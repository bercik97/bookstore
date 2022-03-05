package pl.umcs.bookstore.app.book.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends Repository<Book, Long> {

    void save(Book book);

    Optional<Book> findByTitle(String title);

    List<Book> findAll();
}
