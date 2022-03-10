package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class BookInMemoryRepository implements BookRepository {

    private final ConcurrentHashMap<Long, Book> db;

    private static long idCounter = 0;

    @Override
    public void save(Book book) {
        book.setId(++idCounter);
        db.put(idCounter, book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return db.values()
                .stream()
                .filter(book -> id == book.getId())
                .findFirst();
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return db.values()
                .stream()
                .filter(book -> title.equals(book.getTitle()))
                .findFirst();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(db.values()));
    }

    @Override
    public void deleteById(long id) {
        db.remove(id);
    }
}
