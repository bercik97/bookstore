package pl.umcs.bookstore.app.user.domain

import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto

trait UserFixture {

    static CreateUserDto createUserDto(def username = 'john', def password = '12345!aA', def rePassword = '12345!aA') {
        return new CreateUserDto(username, password, rePassword)
    }
}
