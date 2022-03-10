package pl.umcs.bookstore.app.book.domain.command;

import lombok.Value;

import javax.servlet.http.HttpSession;

@Value(staticConstructor = "of")
public class ManageBookInShoppingCardCommand {

    long bookId;
    HttpSession session;
}
