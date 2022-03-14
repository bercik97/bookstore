package pl.umcs.bookstore.app.user.domain.command;

import lombok.Value;
import pl.umcs.bookstore.app.user.domain.dto.ChangePasswordDto;

@Value
public class ChangePasswordCommand {

    String email;
    String newPassword;
    String reNewPassword;

    public static ChangePasswordCommand of(String email, ChangePasswordDto dto) {
        return new ChangePasswordCommand(email, dto.getPassword(), dto.getRePassword());
    }
}
