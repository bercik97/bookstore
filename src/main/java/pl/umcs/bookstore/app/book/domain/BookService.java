package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;
import pl.umcs.bookstore.app.book.domain.vo.BookVO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class BookService {

    private final BookRepository repository;
    private final BookValidator validator;

    public List<BookVO> findAll() {
        return repository.findAll()
                .stream()
                .map(Book::convert)
                .collect(Collectors.toList());
    }

    public void create(CreateBookDto dto, BindingResult bindingResult) {
        validator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return;
        }
        repository.save(BookFactory.create(dto));
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
