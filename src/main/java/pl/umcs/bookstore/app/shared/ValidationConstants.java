package pl.umcs.bookstore.app.shared;

public interface ValidationConstants {

    interface Field {
        String EMAIL = "email";
        String PASSWORD = "password";
        String RE_PASSWORD = "rePassword";
        String TITLE = "title";
        String AUTHOR = "author";
        String PRICE = "price";
    }

    interface Error {
        String EMAIL_IS_REQUIRED = "EMAIL_IS_REQUIRED";
        String EMAIL_WRONG_FORMAT = "EMAIL_WRONG_FORMAT";
        String EMAIL_TAKEN = "EMAIL_TAKEN";
        String PASSWORD_IS_REQUIRED = "PASSWORD_IS_REQUIRED";
        String PASSWORD_NOT_SAFE = "PASSWORD_NOT_SAFE";
        String RE_PASSWORD_IS_REQUIRED = "RE_PASSWORD_IS_REQUIRED";
        String RE_PASSWORD_DO_NOT_MATCH_PASSWORD = "RE_PASSWORD_DO_NOT_MATCH_PASSWORD";
        String TITLE_IS_REQUIRED = "TITLE_IS_REQUIRED";
        String TITLE_TAKEN = "TITLE_TAKEN";
        String AUTHOR_IS_REQUIRED = "AUTHOR_IS_REQUIRED";
        String PRICE_CANNOT_BE_LOWER_OR_EQUAL_ZERO = "PRICE_CANNOT_BE_LOWER_OR_EQUAL_ZERO";
    }
}
