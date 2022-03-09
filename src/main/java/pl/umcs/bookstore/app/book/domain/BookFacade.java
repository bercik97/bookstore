package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;

@RequiredArgsConstructor
public class BookFacade {

    private final BookService service;

    public Page<Book> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    public void create(CreateBookDto dto, BindingResult bindingResult) {
        service.create(dto, bindingResult);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }
}
