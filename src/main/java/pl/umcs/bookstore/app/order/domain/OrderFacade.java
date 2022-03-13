package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;
import pl.umcs.bookstore.app.order.domain.command.UpdateOrderStatusCommand;
import pl.umcs.bookstore.app.order.domain.dto.OrderDetailsDto;
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

    public Page<OrderOverviewDto> findAllByUserEmail(String userEmail, Pageable pageable) {
        return service.findAllByUserEmail(userEmail, pageable);
    }

    public OrderDetailsDto findById(long id) {
        return service.findById(id);
    }

    public OrderDetailsDto findByUserEmailAndId(String userEmail, long id) {
        return service.findByUserEmailAndId(userEmail, id);
    }

    public void updateStatus(UpdateOrderStatusCommand command) {
        service.updateStatus(command);
    }

    public void deleteById(long id) {
        service.deleteById(id);
    }
}
