package pl.umcs.bookstore.app.book.domain.vo;

import lombok.Value;

@Value(staticConstructor = "of")
public class BookVO {

    long id;
    String createdDate;
    String title;
    String author;
}
