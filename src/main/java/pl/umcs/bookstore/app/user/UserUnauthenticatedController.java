package pl.umcs.bookstore.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.umcs.bookstore.app.security.dto.LoginDto;
import pl.umcs.bookstore.app.user.domain.dto.RegisterDto;

@Controller
class UserUnauthenticatedController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "unauthenticated/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "unauthenticated/register";
    }
}
