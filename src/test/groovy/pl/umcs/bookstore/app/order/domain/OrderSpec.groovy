package pl.umcs.bookstore.app.order.domain

import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.util.concurrent.ConcurrentHashMap

class OrderSpec extends Specification implements OrderFixture {

    private OrderFacade orderFacade

    private ConcurrentHashMap<Long, Order> db

    def setup() {
        db = new ConcurrentHashMap<>()
        orderFacade = new OrderConfiguration().orderFacade(db)
    }

    def 'Should create new order'() {
        given:
        def dto = createSummarizeShoppingCardDto()

        when:
        orderFacade.create(dto)

        then:
        !db.isEmpty()
    }

    def 'Should find all orders'() {
        given:
        db.put(1L, createOrder())

        when:
        def foundOrders = orderFacade.findAll(Pageable.unpaged())

        then:
        !foundOrders.isEmpty() && foundOrders.size() == db.size()
    }

    def 'Should delete order by id'() {
        given:
        def orderId = 1L
        db.put(orderId, createOrder())

        when:
        orderFacade.deleteById(orderId)

        then:
        db.isEmpty() && db.get(orderId) == null
    }
}
