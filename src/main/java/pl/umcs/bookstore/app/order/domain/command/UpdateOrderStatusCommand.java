package pl.umcs.bookstore.app.order.domain.command;

import lombok.Value;
import pl.umcs.bookstore.app.order.domain.OrderStatus;

@Value(staticConstructor = "of")
public class UpdateOrderStatusCommand {

    long id;
    OrderStatus newStatus;
}
