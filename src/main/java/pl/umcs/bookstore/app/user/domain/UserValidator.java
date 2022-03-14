package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.shared.ValidationConstants;
import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

import java.util.regex.Pattern;

@RequiredArgsConstructor
class UserValidator {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$");

    private final UserRepository repository;

    public void validate(CreateUserDto dto, BindingResult bindingResult) {
        validateEmail(dto.getEmail(), bindingResult);
        validatePassword(dto.getPassword(), bindingResult);
        validateRePassword(dto.getPassword(), dto.getRePassword(), bindingResult);
    }

    public void validate(ChangePasswordCommand command, BindingResult bindingResult) {
        validatePassword(command.getNewPassword(), bindingResult);
        validateRePassword(command.getNewPassword(), command.getReNewPassword(), bindingResult);
    }

    private void validateEmail(String email, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.EMAIL;
        if (Strings.isBlank(email)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.EMAIL_IS_REQUIRED);
        } else if (!EMAIL_REGEX.matcher(email).matches()) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.EMAIL_WRONG_FORMAT);
        } else if (repository.findByEmail(email).isPresent()) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.EMAIL_TAKEN);
        }
    }

    private void validatePassword(String password, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.PASSWORD;
        if (Strings.isBlank(password)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.PASSWORD_IS_REQUIRED);
        } else if (!PASSWORD_REGEX.matcher(password).matches()) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.PASSWORD_NOT_SAFE);
        }
    }

    private void validateRePassword(String password, String rePassword, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.RE_PASSWORD;
        if (Strings.isBlank(rePassword)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.RE_PASSWORD_IS_REQUIRED);
        } else if (!rePassword.equals(password)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.RE_PASSWORD_DO_NOT_MATCH_PASSWORD);
        }
    }
}
