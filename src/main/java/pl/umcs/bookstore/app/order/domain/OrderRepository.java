package pl.umcs.bookstore.app.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface OrderRepository extends Repository<Order, Long> {

    void save(Order order);

    Page<Order> findAll(Pageable pageable);

    void deleteById(long id);
}