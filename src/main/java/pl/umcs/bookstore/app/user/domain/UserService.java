package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserService {

    private final UserRepository repository;
}
