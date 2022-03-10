package pl.umcs.bookstore.app.book.domain

import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto

trait BookFixture {

    static CreateBookDto createBookDto(def title = 'Steve Jobs bio', def author = 'Walter Isaacson', def price = 9.99) {
        return new CreateBookDto(title, author, price)
    }

    static Book createBook(def title = 'Steve Jobs bio', def author = 'Walter Isaacson', def price = 9.99) {
        def dto = createBookDto(title, author, price)
        return BookFactory.create(dto)
    }
}
