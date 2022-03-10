package pl.umcs.bookstore.app.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BookRepository extends Repository<Book, Long> {

    void save(Book book);

    Optional<Book> findById(long id);

    Optional<Book> findByTitle(String title);

    Page<Book> findAll(Pageable pageable);

    void deleteById(long id);
}
