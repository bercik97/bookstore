package pl.umcs.bookstore.app.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.umcs.bookstore.app.book.domain.command.ManageBookInShoppingCardCommand;
import pl.umcs.bookstore.app.book.domain.command.SummarizeShoppingCardCommand;
import pl.umcs.bookstore.app.book.domain.dto.BookDto;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
class BookSessionService {

    private static final String SHOPPING_CART = "shoppingCart";

    private final BookRepository repository;

    public void addToShoppingCart(ManageBookInShoppingCardCommand command) {
        HttpSession session = command.getSession();
        List<BookDto> books = getBooksFromSession(session);
        BookDto bookDto = repository.findById(command.getBookId())
                .map(book -> BookDto.from(book, true))
                .orElseThrow(() -> new RuntimeException("Cannot find book by id!"));
        books.add(bookDto);
        log.info("Book {} has been added to shopping card", bookDto);
    }

    public void removeFromShoppingCart(ManageBookInShoppingCardCommand command) {
        getBooksFromSession(command.getSession()).removeIf(book -> {
            log.info("Book {} has been removed from shopping card", book);
            return book.getId() == command.getBookId();
        });
    }

    public Page<BookDto> findAll(Pageable pageable, HttpSession session) {
        List<Long> booksIdsFromSession = getBooksIdsFromSession(session);
        return repository.findAll(pageable)
                .map(book -> {
                    if (booksIdsFromSession.contains(book.getId())) {
                        return BookDto.from(book, true);
                    }
                    return BookDto.from(book);
                });
    }

    public SummarizeShoppingCardDto summarizeShoppingCard(SummarizeShoppingCardCommand command) {
        List<Book> books = getBooksFromSession(command.getSession())
                .stream()
                .map(Book::from)
                .collect(Collectors.toList());
        double totalPrice = calculateTotalPrice(books);
        return new SummarizeShoppingCardDto(command.getUserEmail(), totalPrice, books);
    }

    private List<Long> getBooksIdsFromSession(HttpSession session) {
        return getBooksFromSession(session)
                .stream()
                .map(BookDto::getId)
                .collect(Collectors.toList());
    }

    private List<BookDto> getBooksFromSession(HttpSession session) {
        List<BookDto> books = (List<BookDto>) session.getAttribute(SHOPPING_CART);
        return books == null ? new LinkedList<>() : books;
    }

    private double calculateTotalPrice(List<Book> books) {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }
}
