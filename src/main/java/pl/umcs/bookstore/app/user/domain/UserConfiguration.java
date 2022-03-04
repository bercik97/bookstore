package pl.umcs.bookstore.app.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
class UserConfiguration {

    @Bean
    public UserFacade userFacade(UserRepository repository) {
        UserValidator validator = new UserValidator(repository);
        UserService service = new UserService(repository, validator);
        return new UserFacade(service);
    }

    public UserFacade userFacade(ConcurrentHashMap<Long, User> db) {
        UserInMemoryRepository repository = new UserInMemoryRepository(db);
        UserValidator validator = new UserValidator(repository);
        UserService service = new UserService(repository, validator);
        return new UserFacade(service);
    }
}
