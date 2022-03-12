package pl.umcs.bookstore.app.book.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.book.domain.Book;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummarizeShoppingCardDto {

    String userEmail;
    double totalPrice;
    List<Book> books;
}
