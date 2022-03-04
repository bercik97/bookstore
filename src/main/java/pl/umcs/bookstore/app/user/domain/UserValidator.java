package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.shared.ValidationConstants;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

import java.util.regex.Pattern;

@RequiredArgsConstructor
class UserValidator {

    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$");

    private final UserRepository repository;

    public void validate(CreateUserDto dto, BindingResult bindingResult) {
        validateUsername(dto.getUsername(), bindingResult);
        validatePassword(dto.getPassword(), bindingResult);
        validateRePassword(dto.getPassword(), dto.getRePassword(), bindingResult);
    }

    private void validateUsername(String username, BindingResult bindingResult) {
        final String fieldName = ValidationConstants.Field.USERNAME;
        if (Strings.isBlank(username)) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.USERNAME_IS_REQUIRED);
        } else if (repository.findByUsername(username).isPresent()) {
            bindingResult.rejectValue(fieldName, ValidationConstants.Error.USERNAME_TAKEN);
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
