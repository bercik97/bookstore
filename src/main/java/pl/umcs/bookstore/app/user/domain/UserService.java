package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand;
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

    public Page<User> findAllByIdIsNot(Pageable pageable, long userId) {
        return repository.findAllByIdIsNot(pageable, userId);
    }

    public void changePassword(ChangePasswordCommand command, BindingResult bindingResult) {
        validator.validate(command, bindingResult);
        repository.updatePassword(command.getEmail(), new BCryptPasswordEncoder().encode(command.getNewPassword()));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
