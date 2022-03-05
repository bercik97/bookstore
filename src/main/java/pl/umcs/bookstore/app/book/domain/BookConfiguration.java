package pl.umcs.bookstore.app.book.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class BookConfiguration {

    @Bean
    public BookFacade bookFacade(BookRepository repository) {
        BookValidator validator = new BookValidator(repository);
        BookService service = new BookService(repository, validator);
        return new BookFacade(service);
    }

    public BookFacade bookFacade(ConcurrentHashMap<Long, Book> db) {
        BookInMemoryRepository repository = new BookInMemoryRepository(db);
        BookValidator validator = new BookValidator(repository);
        BookService service = new BookService(repository, validator);
        return new BookFacade(service);
    }
}
