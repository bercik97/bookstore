package pl.umcs.bookstore.app.order.domain

import pl.umcs.bookstore.app.book.domain.Book
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto

import java.time.ZonedDateTime

trait OrderFixture {

    static SummarizeShoppingCardDto createSummarizeShoppingCardDto(def userEmail = 'john.doe@mail.com',
                                                                   def totalPrice = 19.99,
                                                                   def books = List.of(
                                                                           new Book('Book X', 'Author X', 9.99),
                                                                           new Book('Book Y', 'Author Y', 10.0))) {
        return new SummarizeShoppingCardDto(userEmail, totalPrice, books)
    }

    static Order createOrder(def id = 1L,
                             def userEmail = 'john.doe@mail.com',
                             def books = List.of(
                                     new Book('Book X', 'Author X', 9.99),
                                     new Book('Book Y', 'Author Y', 10.0)),
                             def status = OrderStatus.DELIVERED) {
        return new Order(id, ZonedDateTime.now(), userEmail, books, status)
    }
}
