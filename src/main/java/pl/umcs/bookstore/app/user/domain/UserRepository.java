package pl.umcs.bookstore.app.user.domain;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<UserEntity, Long> {
}
