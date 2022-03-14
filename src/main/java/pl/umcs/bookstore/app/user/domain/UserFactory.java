package pl.umcs.bookstore.app.user.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.umcs.bookstore.app.role.RoleType;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class UserFactory {

    public static User create(CreateUserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(new BCryptPasswordEncoder().encode(dto.getPassword()))
                .roles(Set.of(RoleType.ROLE_USER.create()))
                .build();
    }
}
