package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;

    public void create(CreateUserDto dto, BindingResult bindingResult) {
        service.create(dto, bindingResult);
    }
}
