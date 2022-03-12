package pl.umcs.bookstore.app.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.umcs.bookstore.app.book.domain.dto.BookDto;
import pl.umcs.bookstore.app.shared.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private double price;

    public Book(long id, ZonedDateTime createdDate, String title, String author, double price) {
        super(id, createdDate);
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static Book from(BookDto dto) {
        return new Book(dto.getId(), dto.getCreatedDate(), dto.getTitle(), dto.getAuthor(), dto.getPrice());
    }
}
