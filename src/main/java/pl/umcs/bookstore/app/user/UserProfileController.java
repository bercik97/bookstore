package pl.umcs.bookstore.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.umcs.bookstore.app.security.model.CustomUserDetails;
import pl.umcs.bookstore.app.user.domain.User;
import pl.umcs.bookstore.app.user.domain.UserFacade;
import pl.umcs.bookstore.app.user.domain.command.ChangePasswordCommand;
import pl.umcs.bookstore.app.user.domain.dto.ChangePasswordDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/profile")
class UserProfileController {

    private final UserFacade facade;

    @GetMapping
    public String profilePage(Model model, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        model.addAttribute("user", user);
        model.addAttribute("changePasswordDto", new ChangePasswordDto());
        return "authenticated/profile";
    }

    @PostMapping
    public String changePassword(Model model, @ModelAttribute("changePasswordDto") ChangePasswordDto dto, BindingResult bindingResult, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        facade.changePassword(ChangePasswordCommand.of(user.getUsername(), dto), bindingResult);
        model.addAttribute("user", user);
        if (bindingResult.hasErrors()) {
            model.addAttribute("changePasswordDto", dto);
            return "authenticated/profile";
        }
        return "authenticated/profile";
    }
}
