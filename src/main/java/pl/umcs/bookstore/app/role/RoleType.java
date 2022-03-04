package pl.umcs.bookstore.app.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    ROLE_USER(1L, "ROLE_USER", "USER") {
        @Override
        public Role create() {
            return new Role(1L, "ROLE_USER");
        }
    },
    ROLE_ADMIN(2L, "ROLE_ADMIN", "ADMIN") {
        @Override
        public Role create() {
            return new Role(2L, "ROLE_ADMIN");
        }
    };

    private final long id;
    private final String name;
    private final String nameWithoutPrefix;

    public abstract Role create();
}
