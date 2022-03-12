package pl.umcs.bookstore.app.book.domain.command;

import lombok.Value;

import javax.servlet.http.HttpSession;

@Value(staticConstructor = "of")
public class SummarizeShoppingCardCommand {

    String userEmail;
    HttpSession session;
}
