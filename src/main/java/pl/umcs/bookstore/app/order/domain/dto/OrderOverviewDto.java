package pl.umcs.bookstore.app.order.domain.dto;

import lombok.Value;
import pl.umcs.bookstore.app.order.domain.Order;
import pl.umcs.bookstore.app.order.domain.OrderStatus;

import java.time.ZonedDateTime;

@Value
public class OrderOverviewDto {

    long id;
    ZonedDateTime createdDate;
    String userEmail;
    int numberOfBooks;
    double totalPrice;
    OrderStatus status;

    public static OrderOverviewDto from(Order order, double totalPrice) {
        return new OrderOverviewDto(order.getId(), order.getCreatedDate(), order.getUserEmail(), order.getBooks().size(), totalPrice, order.getStatus());
    }
}
