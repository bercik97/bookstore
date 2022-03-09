package pl.umcs.bookstore.app.user.domain.command;

import lombok.Value;
import pl.umcs.bookstore.app.user.domain.dto.ChangePasswordDto;

@Value
public class ChangePasswordCommand {

    String username;
    String newPassword;
    String reNewPassword;

    public static ChangePasswordCommand of(String username, ChangePasswordDto dto) {
        return new ChangePasswordCommand(username, dto.getPassword(), dto.getRePassword());
    }
}
