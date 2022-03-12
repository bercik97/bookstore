package pl.umcs.bookstore.app.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {

    void save(Order order);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByUserEmail(String userEmail, Pageable pageable);

    Optional<Order> findByUserEmailAndId(String userEmail, long id);

    void deleteById(long id);
}
