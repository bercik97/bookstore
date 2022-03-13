package pl.umcs.bookstore.app.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface OrderRepository extends Repository<Order, Long> {

    void save(Order order);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByUserEmail(String userEmail, Pageable pageable);

    Optional<Order> findByUserEmailAndId(String userEmail, long id);

    Optional<Order> findById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :newStatus WHERE o.id = :id")
    void updateStatus(@Param("newStatus") OrderStatus newStatus, @Param("id") long id);

    void deleteById(long id);
}
