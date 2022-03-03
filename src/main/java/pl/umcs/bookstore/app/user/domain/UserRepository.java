package pl.umcs.bookstore.app.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
