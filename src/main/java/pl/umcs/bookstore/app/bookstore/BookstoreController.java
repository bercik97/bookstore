package pl.umcs.bookstore.app.bookstore;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class BookstoreController {

    @GetMapping("/bookstore")
    public String bookstorePage() {
        return "authenticated/bookstore";
    }
}
