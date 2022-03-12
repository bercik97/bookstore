package pl.umcs.bookstore.app.order.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderFactory {

    public static Order create(SummarizeShoppingCardDto dto) {
        return Order.builder()
                .userEmail(dto.getUserEmail())
                .books(dto.getBooks())
                .status(OrderStatus.UNPAID)
                .build();
    }
}
