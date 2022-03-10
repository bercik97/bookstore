package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.command.ManageBookInShoppingCardCommand;
import pl.umcs.bookstore.app.book.domain.dto.BookDto;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
public class BookFacade {

    private final BookService service;
    private final BookSessionService sessionService;

    public Page<BookDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    public void create(CreateBookDto dto, BindingResult bindingResult) {
        service.create(dto, bindingResult);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }

    public Page<BookDto> findAll(Pageable pageable, HttpSession session) {
        return sessionService.findAll(pageable, session);
    }

    public void addToShoppingCart(ManageBookInShoppingCardCommand command) {
        sessionService.addToShoppingCart(command);
    }

    public void removeFromShoppingCart(ManageBookInShoppingCardCommand command) {
        sessionService.removeFromShoppingCart(command);
    }
}
