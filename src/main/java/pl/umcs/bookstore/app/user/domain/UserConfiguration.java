package pl.umcs.bookstore.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class UserConfiguration {

    @Bean
    public UserFacade userFacade(UserRepository repository) {
        UserService service = new UserService(repository);
        return new UserFacade(service);
    }

    public UserFacade userFacade(ConcurrentHashMap<Long, UserEntity> db) {
        UserInMemoryRepository repository = new UserInMemoryRepository(db);
        UserService service = new UserService(repository);
        return new UserFacade(service);
    }
}
