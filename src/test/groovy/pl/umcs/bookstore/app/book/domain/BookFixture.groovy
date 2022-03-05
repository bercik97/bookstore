package pl.umcs.bookstore.app.book.domain

import pl.umcs.bookstore.app.book.domain.dto.CreateBookDto

trait BookFixture {

    static CreateBookDto createBookDto(def title = 'Steve Jobs bio', def author = 'Walter Isaacson') {
        return new CreateBookDto(title, author)
    }

    static Book createBook(def title = 'Steve Jobs bio', def author = 'Walter Isaacson') {
        def dto = createBookDto(title, author)
        return BookFactory.create(dto)
    }
}
