package pl.umcs.bookstore.app.user.domain.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String password;
    private String rePassword;
}
