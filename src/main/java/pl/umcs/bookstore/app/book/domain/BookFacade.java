package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;
import pl.umcs.bookstore.app.book.domain.vo.BookVO;

import java.util.List;

@RequiredArgsConstructor
public class BookFacade {

    private final BookService service;

    public List<BookVO> findAll() {
        return service.findAll();
    }

    public void create(CreateBookDto dto, BindingResult bindingResult) {
        service.create(dto, bindingResult);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }
}
