package pl.umcs.bookstore.app.user.domain

import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand
import pl.umcs.bookstore.app.user.domain.dto.ChangePasswordDto
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto

trait UserFixture {

    static CreateUserDto createUserDto(def email = 'john.doe@mail.com', def password = '12345!aA', def rePassword = '12345!aA') {
        return new CreateUserDto(email, password, rePassword)
    }

    static User createUser(def email = 'john.doe@mail.com', def password = '12345!aA', def rePassword = '12345!aA') {
        def dto = createUserDto(email, password, rePassword)
        return UserFactory.create(dto)
    }

    static ChangePasswordCommand createChangePasswordCommand(def email = 'john.doe@mail.com', def newPassword = '12345!aA', def reNewPassword = '12345!aA') {
        return ChangePasswordCommand.of(email, new ChangePasswordDto(newPassword, reNewPassword))
    }
}
