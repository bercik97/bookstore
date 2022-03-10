package pl.umcs.bookstore.app.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.book.domain.Book;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;
    private ZonedDateTime createdDate;
    private String title;
    private String author;
    private double price;
    private boolean isAddedToSession;

    public static BookDto from(Book book) {
        return new BookDto(book.getId(), book.getCreatedDate(), book.getTitle(), book.getAuthor(), book.getPrice(), false);
    }

    public static BookDto from(Book book, boolean isAddedToSession) {
        return new BookDto(book.getId(), book.getCreatedDate(), book.getTitle(), book.getAuthor(), book.getPrice(), isAddedToSession);
    }
}
