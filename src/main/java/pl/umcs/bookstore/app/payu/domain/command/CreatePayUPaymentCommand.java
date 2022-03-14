package pl.umcs.bookstore.app.payu.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.order.domain.dto.OrderDetailsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePayUPaymentCommand {

    private String customerIp;
    private String email;
    private Integer productPrice;
    private List<Book> books;

    public static CreatePayUPaymentCommand of(OrderDetailsDto order, String userEmail, HttpServletRequest request) {
        return new CreatePayUPaymentCommand(request.getRemoteAddr(), userEmail, Double.valueOf(order.getTotalPrice()).intValue() * 100, order.getBooks());
    }
}
