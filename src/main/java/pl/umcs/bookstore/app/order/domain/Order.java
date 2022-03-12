package pl.umcs.bookstore.app.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.shared.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Column(nullable = false)
    private String userEmail;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
}
