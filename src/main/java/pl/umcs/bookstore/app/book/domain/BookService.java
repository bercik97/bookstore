package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;

@RequiredArgsConstructor
class BookService {

    private final BookRepository repository;
    private final BookValidator validator;

    public Page<Book> findAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
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
