package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;

    public void create(CreateUserDto dto, BindingResult bindingResult) {
        service.create(dto, bindingResult);
    }

    public Page<User> findAllByIdIsNot(Pageable pageable, long userId) {
        return service.findAllByIdIsNot(pageable, userId);
    }

    public void changePassword(ChangePasswordCommand command, BindingResult bindingResult) {
        service.changePassword(command, bindingResult);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }
}
