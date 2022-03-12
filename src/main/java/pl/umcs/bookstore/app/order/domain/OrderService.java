package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;
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

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    private double calculateTotalPrice(List<Book> books) {
        return books.stream()
                .mapToDouble(Book::getPrice)
                .sum();
    }
}
