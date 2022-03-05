package pl.umcs.bookstore.app.book.domain.vo;

import lombok.Value;

import java.time.ZonedDateTime;

@Value(staticConstructor = "of")
public class BookVO {

    long id;
    ZonedDateTime createdDate;
    String title;
    String author;
}
