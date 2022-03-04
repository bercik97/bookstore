package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

@RequiredArgsConstructor
class UserService {

    private final UserRepository repository;
    private final UserValidator validator;

    public void create(CreateUserDto dto, BindingResult bindingResult) {
        validator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return;
        }
        repository.save(UserFactory.create(dto));
    }
}
