package pl.umcs.bookstore.app.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.umcs.bookstore.app.shared.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    private String role;

    public Role(long id, String role) {
        super(id, ZonedDateTime.now());
        this.role = role;
    }
}
