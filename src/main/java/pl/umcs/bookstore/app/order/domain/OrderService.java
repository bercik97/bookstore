package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;
import pl.umcs.bookstore.app.order.domain.command.UpdateOrderStatusCommand;
import pl.umcs.bookstore.app.order.domain.dto.OrderDetailsDto;
import pl.umcs.bookstore.app.order.domain.dto.OrderOverviewDto;

import java.util.List;

@RequiredArgsConstructor
class OrderService {

    private final OrderRepository repository;

    public void create(SummarizeShoppingCardDto dto) {
        repository.save(OrderFactory.create(dto));
    }

    public Page<OrderOverviewDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(order -> {
                    double totalPrice = calculateTotalPrice(order.getBooks());
                    return OrderOverviewDto.from(order, totalPrice);
                });
    }

    public Page<OrderOverviewDto> findAllByUserEmail(String userEmail, Pageable pageable) {
        return repository.findAllByUserEmail(userEmail, pageable)
                .map(order -> {
                    double totalPrice = calculateTotalPrice(order.getBooks());
                    return OrderOverviewDto.from(order, totalPrice);
                });
    }

    public OrderDetailsDto findByUserEmailAndId(String userEmail, long id) {
        return repository.findByUserEmailAndId(userEmail, id)
                .map(order -> {
                    double totalPrice = calculateTotalPrice(order.getBooks());
                    return OrderDetailsDto.from(order, totalPrice);
                })
                .orElseThrow(() -> new RuntimeException("Cannot find order by userEmail and id: " + userEmail + ", " + id));
    }

    public OrderDetailsDto findById(long id) {
        return repository.findById(id)
                .map(order -> {
                    double totalPrice = calculateTotalPrice(order.getBooks());
                    return OrderDetailsDto.from(order, totalPrice);
                })
                .orElseThrow(() -> new RuntimeException("Cannot find order id: " + id));
    }

    public void updateStatus(UpdateOrderStatusCommand command) {
        repository.updateStatus(command.getNewStatus(), command.getId());
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private double calculateTotalPrice(List<Book> books) {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }
}
