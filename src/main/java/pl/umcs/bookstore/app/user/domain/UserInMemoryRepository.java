package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private final ConcurrentHashMap<Long, UserEntity> db;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return db.values()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}
