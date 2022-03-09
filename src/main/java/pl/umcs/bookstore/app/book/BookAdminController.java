package pl.umcs.bookstore.app.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.umcs.bookstore.app.book.domain.BookFacade;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-panel/books")
class BookAdminController {

    private final BookFacade facade;

    @GetMapping
    public String booksPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("books", facade.findAll(PageRequest.of(page, 10)));
        model.addAttribute("currentPage", page);
        return "authenticated/admin/books";
    }

    @GetMapping("/add")
    public String addBookPage(Model model) {
        model.addAttribute("createBookDto", new CreateBookDto());
        return "authenticated/admin/add-book";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("createBookDto") CreateBookDto dto, BindingResult bindingResult) {
        facade.create(dto, bindingResult);
        if (!bindingResult.hasErrors()) {
            return "redirect:/admin-panel/books";
        }
        model.addAttribute("createBookDto", dto);
        return "authenticated/admin/add-book";
    }

    @PostMapping("{id}")
    public String deleteById(@PathVariable long id) {
        facade.deleteById(id);
        return "redirect:/admin-panel/books";
    }
}
