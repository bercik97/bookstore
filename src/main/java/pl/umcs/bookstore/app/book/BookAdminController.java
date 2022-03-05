package pl.umcs.bookstore.app.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.umcs.bookstore.app.book.domain.BookFacade;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;
import pl.umcs.bookstore.app.book.domain.vo.BookVO;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-panel/books")
class BookAdminController {

    private final BookFacade facade;

    @GetMapping
    public String booksPage(Model model) {
        List<BookVO> books = facade.findAll();
        model.addAttribute("books", books);
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("createBookDto", dto);
            return "authenticated/admin/add-book";
        }
        model.addAttribute("successBookAdded", true);
        return "authenticated/admin/books";
    }
}
