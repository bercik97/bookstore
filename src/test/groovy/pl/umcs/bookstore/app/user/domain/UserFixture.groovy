package pl.umcs.bookstore.app.user.domain

import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand
import pl.umcs.bookstore.app.user.domain.dto.ChangePasswordDto
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto

trait UserFixture {

    static CreateUserDto createUserDto(def username = 'john', def password = '12345!aA', def rePassword = '12345!aA') {
        return new CreateUserDto(username, password, rePassword)
    }

    static User createUser(def username = 'john', def password = '12345!aA', def rePassword = '12345!aA') {
        def dto = createUserDto(username, password, rePassword)
        return UserFactory.create(dto)
    }

    static ChangePasswordCommand createChangePasswordCommand(def username = 'john', def newPassword = '12345!aA', def reNewPassword = '12345!aA') {
        return ChangePasswordCommand.of(username, new ChangePasswordDto(newPassword, reNewPassword))
    }
}
