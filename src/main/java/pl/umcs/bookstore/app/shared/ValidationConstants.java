package pl.umcs.bookstore.app.shared;

public interface ValidationConstants {

    interface Field {
        String USERNAME = "username";
        String PASSWORD = "password";
        String RE_PASSWORD = "rePassword";
        String TITLE = "title";
        String AUTHOR = "author";
    }

    interface Error {
        String USERNAME_IS_REQUIRED = "USERNAME_IS_REQUIRED";
        String USERNAME_TAKEN = "USERNAME_TAKEN";
        String PASSWORD_IS_REQUIRED = "PASSWORD_IS_REQUIRED";
        String PASSWORD_NOT_SAFE = "PASSWORD_NOT_SAFE";
        String RE_PASSWORD_IS_REQUIRED = "RE_PASSWORD_IS_REQUIRED";
        String RE_PASSWORD_DO_NOT_MATCH_PASSWORD = "RE_PASSWORD_DO_NOT_MATCH_PASSWORD";
        String TITLE_IS_REQUIRED = "TITLE_IS_REQUIRED";
        String TITLE_TAKEN = "TITLE_TAKEN";
        String AUTHOR_IS_REQUIRED = "AUTHOR_IS_REQUIRED";
    }
}
