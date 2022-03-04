package pl.umcs.bookstore.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.umcs.bookstore.app.security.dto.LoginDto;
import pl.umcs.bookstore.app.user.domain.UserFacade;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

@Controller
@RequiredArgsConstructor
class UserUnauthenticatedController {

    private final UserFacade facade;

    @GetMapping(value = {"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "unauthenticated/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new CreateUserDto());
        return "unauthenticated/register";
    }

    @PostMapping("/register")
    public String create(Model model, @ModelAttribute("registerDto") CreateUserDto dto, BindingResult bindingResult) {
        facade.create(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerDto", dto);
            return "unauthenticated/register";
        }
        model.addAttribute("successRegistration", true);
        return "unauthenticated/login";
    }
}
