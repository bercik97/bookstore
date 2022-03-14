package pl.umcs.bookstore.app.user.domain

import org.springframework.data.domain.Pageable
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.BindingResult
import pl.umcs.bookstore.app.shared.ValidationConstants
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

class UserSpec extends Specification implements UserFixture {

    private UserFacade userFacade

    private ConcurrentHashMap<Long, User> db

    def setup() {
        db = new ConcurrentHashMap<>()
        userFacade = new UserConfiguration().userFacade(db)
    }

    def 'Should create new user'() {
        given:
        def dto = createUserDto()

        when:
        userFacade.create(dto, Mock(BindingResult))

        then:
        !db.isEmpty()
    }

    def 'Should receive specific error cause email need to be unique'() {
        given:
        def dto = createUserDto()
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when:
        userFacade.create(dto, bindingResult)

        and: 'we try to create user with the same email as before'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.EMAIL).code == ValidationConstants.Error.EMAIL_TAKEN
    }

    def 'Should receive specific error cause password and re password do not match'() {
        given:
        def password = '12345!aA'
        def rePassword = '12345!bB'
        def dto = createUserDto('john.doe@mail.com', password, rePassword)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when:
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.RE_PASSWORD).code == ValidationConstants.Error.RE_PASSWORD_DO_NOT_MATCH_PASSWORD
    }

    @Unroll
    def 'Should receive specific error cause some of fields are invalid'(field, expectedError,
                                                                         email, password, rePassword) {
        given:
        def dto = createUserDto(email, password, rePassword)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when: 'we try to create an user'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(field).code == expectedError

        where:
        field                                 | expectedError                                     | email  | password   | rePassword | _
        ValidationConstants.Field.EMAIL       | ValidationConstants.Error.EMAIL_IS_REQUIRED       | null   | '12345Aa!' | '12345Aa!' | _
        ValidationConstants.Field.PASSWORD    | ValidationConstants.Error.PASSWORD_IS_REQUIRED    | 'john' | null       | '12345Aa!' | _
        ValidationConstants.Field.RE_PASSWORD | ValidationConstants.Error.RE_PASSWORD_IS_REQUIRED | 'john' | '12345Aa!' | null       | _
    }

    @Unroll
    def 'Should throw an exception cause email format = [#email]'(String email) {
        given:
        def dto = createUserDto(email)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when: 'we try to create an user'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.EMAIL).code == ValidationConstants.Error.EMAIL_WRONG_FORMAT

        where:
        email                          | _
        'plainaddress'                 | _
        '#@%^%#$@#$@#.com'             | _
        '@domain.com'                  | _
        'Joe Smith <email@domain.com>' | _
        'email.domain.com'             | _
        'email@domain@domain.com'      | _
        '.email@domain.com'            | _
        'email.@domain.com'            | _
        'email..email@domain.com'      | _
        'あいうえお@domain.com'             | _
        'email@domain.com (Joe Smith)' | _
        'email@domain'                 | _
    }

    @Unroll
    def 'Should receive specific error cause password is not safe = [#password]'(String password) {
        given:
        def dto = createUserDto('john.doe@gmail.com', password)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when: 'we try to create user'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.PASSWORD).code == ValidationConstants.Error.PASSWORD_NOT_SAFE

        where:
        password     | _
        '1234'       | _
        '12345678'   | _
        'qwerty12'   | _
        'qwerty1!'   | _
        'qwerty1YY1' | _
    }

    def 'Should find all users'() {
        given:
        userFacade.create(createUserDto(), Mock(BindingResult))

        when:
        def foundUsers = userFacade.findAllByIdIsNot(Pageable.unpaged(), 999L)

        then:
        !foundUsers.isEmpty() && foundUsers.size() == db.size()
    }

    def 'Should change user password'() {
        given:
        def createUserDto = createUserDto()
        def oldPassword = createUserDto.password
        def newPassword = '12345!aB'
        def command = createChangePasswordCommand(createUserDto.email, newPassword, newPassword)
        userFacade.create(createUserDto, Mock(BindingResult))

        when:
        userFacade.changePassword(command, Mock(BindingResult))

        then:
        def updatedUser = db.values().stream().filter(user -> user.email == createUserDto.email).findFirst().get()
        updatedUser.password != oldPassword
    }

    def 'Should delete user by id'() {
        given:
        def userId = 1L
        db.put(userId, createUser())

        when:
        userFacade.deleteById(userId)

        then:
        db.isEmpty() && db.get(userId) == null
    }
}
