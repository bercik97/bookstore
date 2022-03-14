package pl.umcs.bookstore.app.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    void save(User user);

    Optional<User> findByEmail(String email);

    Page<User> findAllByIdIsNot(Pageable pageable, long userId);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email = :email")
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);

    void deleteById(long id);
}
