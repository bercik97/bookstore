package pl.umcs.bookstore.app.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.umcs.bookstore.app.book.domain.vo.BookVO;
import pl.umcs.bookstore.app.shared.BaseEntity;
import pl.umcs.bookstore.app.shared.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity implements Query<BookVO> {

    @Column(unique = true, nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;

    @Override
    public BookVO convert() {
        return BookVO.of(getId(), getCreatedDate(), this.title, this.author);
    }
}
