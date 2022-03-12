package pl.umcs.bookstore.app.order.domain.dto;

import lombok.Value;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.order.domain.Order;
import pl.umcs.bookstore.app.order.domain.OrderStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class OrderDetailsDto {

    long id;
    ZonedDateTime createdDate;
    double totalPrice;
    OrderStatus status;
    List<Book> books;

    public static OrderDetailsDto from(Order order, double totalPrice) {
        return new OrderDetailsDto(order.getId(), order.getCreatedDate(), totalPrice, order.getStatus(), order.getBooks());
    }
}
