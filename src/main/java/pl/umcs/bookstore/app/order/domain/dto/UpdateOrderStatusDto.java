package pl.umcs.bookstore.app.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.order.domain.OrderStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusDto {

    private OrderStatus status;
}
