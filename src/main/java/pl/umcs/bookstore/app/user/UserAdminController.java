package pl.umcs.bookstore.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.umcs.bookstore.app.security.model.CustomUserDetails;
import pl.umcs.bookstore.app.user.domain.UserFacade;
import pl.umcs.bookstore.app.user.domain.dto.CreateUserDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-panel/users")
class UserAdminController {

    private final UserFacade facade;

    @GetMapping
    public String usersPage(Model model, Authentication authentication, @RequestParam(defaultValue = "0") int page) {
        long userId = ((CustomUserDetails) authentication.getPrincipal()).getUser().getId();
        model.addAttribute("users", facade.findAllByIdIsNot(PageRequest.of(page, 10), userId));
        model.addAttribute("currentPage", page);
        return "authenticated/admin/users";
    }

    @GetMapping("/add")
    public String addUserPage(Model model) {
        model.addAttribute("createUserDto", new CreateUserDto());
        return "authenticated/admin/add-user";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("createUserDto") CreateUserDto dto, BindingResult bindingResult) {
        facade.create(dto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return "redirect:/admin-panel/users";
        }
        model.addAttribute("createUserDto", dto);
        return "authenticated/admin/add-user";
    }

    @PostMapping("{id}")
    public String deleteById(@PathVariable long id) {
        facade.deleteById(id);
        return "redirect:/admin-panel/users";
    }
}
