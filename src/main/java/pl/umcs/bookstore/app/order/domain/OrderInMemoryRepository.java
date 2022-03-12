package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class OrderInMemoryRepository implements OrderRepository {

    private final ConcurrentHashMap<Long, Order> db;

    private static long idCounter = 0;

    @Override
    public void save(Order order) {
        order.setId(++idCounter);
        db.put(idCounter, order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(db.values()));
    }

    @Override
    public Page<Order> findAllByUserEmail(String userEmail, Pageable pageable) {
        List<Order> orders = db.values()
                .stream()
                .filter(order -> order.getUserEmail().equals(userEmail))
                .collect(Collectors.toList());
        return new PageImpl<>(new ArrayList<>(orders));
    }

    @Override
    public Optional<Order> findByUserEmailAndId(String userEmail, long id) {
        return db.values()
                .stream()
                .filter(order -> userEmail.equals(order.getUserEmail()))
                .filter(order -> id == order.getId())
                .findFirst();
    }

    @Override
    public void deleteById(long id) {
        db.remove(id);
    }
}
