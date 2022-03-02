package pl.umcs.bookstore.app.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService service;
}
