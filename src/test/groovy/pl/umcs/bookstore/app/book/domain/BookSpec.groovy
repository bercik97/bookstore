package pl.umcs.bookstore.app.book.domain

import org.springframework.data.domain.Pageable
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.BindingResult
import pl.umcs.bookstore.app.shared.ValidationConstants
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

class BookSpec extends Specification implements BookFixture {

    private BookFacade bookFacade

    private ConcurrentHashMap<Long, Book> db

    def setup() {
        db = new ConcurrentHashMap<>()
        bookFacade = new BookConfiguration().bookFacade(db)
    }

    def 'Should create new book'() {
        given:
        def dto = createBookDto()

        when:
        bookFacade.create(dto, Mock(BindingResult))

        then:
        !db.isEmpty()
    }

    def 'Should receive specific error cause title need to be unique'() {
        given:
        def dto = createBookDto()
        def bindingResult = new BeanPropertyBindingResult(dto, 'createBookDto')

        when:
        bookFacade.create(dto, bindingResult)

        and: 'we try to create book with the same title as before'
        bookFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(ValidationConstants.Field.TITLE).code == ValidationConstants.Error.TITLE_TAKEN
    }

    @Unroll
    def 'Should receive specific error cause some of fields are invalid'(field, expectedError,
                                                                         title, author) {
        given:
        def dto = createBookDto(title, author)
        def bindingResult = new BeanPropertyBindingResult(dto, 'createBookDto')

        when: 'we try to create a book'
        bookFacade.create(dto, bindingResult)

        then: 'binding result contains specific error'
        bindingResult.getFieldError(field).code == expectedError

        where:
        field                            | expectedError                                | title            | author            | _
        ValidationConstants.Field.TITLE  | ValidationConstants.Error.TITLE_IS_REQUIRED  | null             | 'Walter Isaacson' | _
        ValidationConstants.Field.AUTHOR | ValidationConstants.Error.AUTHOR_IS_REQUIRED | 'Steve Jobs bio' | null              | _
    }

    def 'Should find all books'() {
        given:
        db.put(1L, createBook())

        when:
        def foundBooks = bookFacade.findAll(Pageable.unpaged())

        then:
        !foundBooks.isEmpty() && foundBooks.size() == db.size()
    }

    def 'Should delete book by id'() {
        given:
        def bookId = 1L
        db.put(bookId, createBook())

        when:
        bookFacade.deleteById(bookId)

        then:
        db.isEmpty() && db.get(bookId) == null
    }
}
