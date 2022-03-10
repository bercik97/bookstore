package pl.umcs.bookstore.app.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.umcs.bookstore.app.book.domain.BookFacade;
import pl.umcs.bookstore.app.book.domain.command.ManageBookInShoppingCardCommand;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
class BookController {

    private final BookFacade facade;

    @GetMapping
    public String booksPage(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
        model.addAttribute("books", facade.findAll(PageRequest.of(page, 10), session));
        model.addAttribute("currentPage", page);
        return "authenticated/books";
    }

    @PostMapping("add/{id}")
    public String addToShoppingCart(@PathVariable long id, HttpSession session) {
        facade.addToShoppingCart(ManageBookInShoppingCardCommand.of(id, session));
        return "redirect:/books";
    }

    @PostMapping("remove/{id}")
    public String removeFromShoppingCart(@PathVariable long id, HttpSession session) {
        facade.removeFromShoppingCart(ManageBookInShoppingCardCommand.of(id, session));
        return "redirect:/books";
    }
}
