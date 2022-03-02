package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
class UserInMemoryRepository implements UserRepository {

    private final ConcurrentHashMap<Long, UserEntity> db;
}
