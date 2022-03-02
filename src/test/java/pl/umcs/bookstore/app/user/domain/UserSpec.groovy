package pl.umcs.bookstore.app.user.domain

import spock.lang.Specification

import java.util.concurrent.ConcurrentHashMap

class UserSpec extends Specification {

    private UserFacade userFacade

    private ConcurrentHashMap<Long, UserEntity> db

    def setup() {
        db = new ConcurrentHashMap<>()
        userFacade = new UserConfiguration().userFacade(db)
    }
}
