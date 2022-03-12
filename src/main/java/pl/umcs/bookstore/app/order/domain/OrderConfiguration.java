package pl.umcs.bookstore.app.order.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class OrderConfiguration {

    @Bean
    public OrderFacade orderFacade(OrderRepository repository) {
        OrderService service = new OrderService(repository);
        return new OrderFacade(service);
    }

    public OrderFacade orderFacade(ConcurrentHashMap<Long, Order> db) {
        OrderInMemoryRepository repository = new OrderInMemoryRepository(db);
        OrderService service = new OrderService(repository);
        return new OrderFacade(service);
    }
}
