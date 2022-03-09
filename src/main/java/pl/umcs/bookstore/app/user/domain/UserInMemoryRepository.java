package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    @Override
    public Page<User> findAllByIdIsNot(Pageable pageable, long userId) {
        List<User> users = db.values()
                .stream()
                .filter(user -> user.getId() != userId)
                .collect(Collectors.toList());
        return new PageImpl<>(new ArrayList<>(users));
    }

    @Override
    public void deleteById(long id) {
        db.remove(id);
    }
}
