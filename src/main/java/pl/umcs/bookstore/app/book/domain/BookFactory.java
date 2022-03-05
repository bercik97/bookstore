package pl.umcs.bookstore.app.book.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class BookFactory {

    public static Book create(CreateBookDto dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .build();
    }
}
