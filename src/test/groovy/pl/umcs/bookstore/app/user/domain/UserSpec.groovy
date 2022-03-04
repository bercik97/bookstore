package pl.umcs.bookstore.app.user.domain

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

    def 'Should receive specific error cause username need to be unique'() {
        given:
        def dto = createUserDto()
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when:
        userFacade.create(dto, bindingResult)

        and: 'we try to create user with the same username as before'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.USERNAME).code == ValidationConstants.Error.USERNAME_TAKEN
    }

    def 'Should receive specific error cause password and re password do not match'() {
        given:
        def password = '12345!aA'
        def rePassword = '12345!bB'
        def dto = createUserDto('john', password, rePassword)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when:
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.RE_PASSWORD).code == ValidationConstants.Error.RE_PASSWORD_DO_NOT_MATCH_PASSWORD
    }

    @Unroll
    def 'Should receive specific error cause some of fields are invalid'(field, expectedError,
                                                                         username, password, rePassword) {
        given:
        def dto = createUserDto(username, password, rePassword)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createUserDto')

        when: 'we try to create an user'
        userFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(field).code == expectedError

        where:
        field                                 | expectedError                                     | username | password   | rePassword | _
        ValidationConstants.Field.USERNAME    | ValidationConstants.Error.USERNAME_IS_REQUIRED    | null     | '12345Aa!' | '12345Aa!' | _
        ValidationConstants.Field.PASSWORD    | ValidationConstants.Error.PASSWORD_IS_REQUIRED    | 'john'   | null       | '12345Aa!' | _
        ValidationConstants.Field.RE_PASSWORD | ValidationConstants.Error.RE_PASSWORD_IS_REQUIRED | 'john'   | '12345Aa!' | null       | _
    }

    @Unroll
    def 'Should receive specific error cause password is not safe = [#password]'(String password) {
        given:
        def dto = createUserDto('john', password)
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
}
