package pl.umcs.bookstore.app.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

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
    public void deleteById(long id) {
        db.remove(id);
    }
}
