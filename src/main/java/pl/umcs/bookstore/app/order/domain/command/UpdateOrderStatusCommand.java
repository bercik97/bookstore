package pl.umcs.bookstore.app.order.domain.command;

import lombok.Value;
import pl.umcs.bookstore.app.order.domain.OrderStatus;
import pl.umcs.bookstore.app.order.domain.dto.UpdateOrderStatusDto;

@Value
public class UpdateOrderStatusCommand {

    long id;
    OrderStatus newStatus;

    public static UpdateOrderStatusCommand of(long orderId, UpdateOrderStatusDto dto) {
        return new UpdateOrderStatusCommand(orderId, dto.getStatus());
    }
}
