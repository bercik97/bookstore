package pl.umcs.bookstore.app.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    void save(User user);

    Optional<User> findByUsername(String username);
}
