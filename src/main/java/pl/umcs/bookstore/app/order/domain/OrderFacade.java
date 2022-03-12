package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;
import pl.umcs.bookstore.app.order.domain.dto.OrderOverviewDto;

@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService service;

    public void create(SummarizeShoppingCardDto dto) {
        service.create(dto);
    }

    public Page<OrderOverviewDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }
}
