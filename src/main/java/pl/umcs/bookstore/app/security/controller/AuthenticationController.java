package pl.umcs.bookstore.app.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.umcs.bookstore.app.security.dto.LoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
class AuthenticationController {

    private static final String LOGIN_PAGE = "unauthenticated/login";

    @GetMapping(value = {"/", "/login"})
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
            securityContextLogoutHandler.logout(request, response, auth);
            log.info("User {} has been logged out", auth.getName());
        }
        return LOGIN_PAGE;
    }
}
