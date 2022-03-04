package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private final ConcurrentHashMap<Long, User> db;

    private static long idCounter = 0;

    @Override
    public void save(User user) {
        user.setId(++idCounter);
        db.put(idCounter, user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return db.values()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }
}
