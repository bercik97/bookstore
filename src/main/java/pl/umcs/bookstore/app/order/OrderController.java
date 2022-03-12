package pl.umcs.bookstore.app.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.book.domain.BookFacade;
import pl.umcs.bookstore.app.book.domain.command.SummarizeShoppingCardCommand;
import pl.umcs.bookstore.app.book.domain.dto.SummarizeShoppingCardDto;
import pl.umcs.bookstore.app.order.domain.OrderFacade;
import pl.umcs.bookstore.app.security.model.CustomUserDetails;
import pl.umcs.bookstore.app.user.domain.User;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
class OrderController {

    private final OrderFacade orderFacade;
    private final BookFacade bookFacade;

    @GetMapping("/summary")
    public String orderSummarizePage(Model model, HttpSession session, Authentication authentication) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        SummarizeShoppingCardDto summarize = bookFacade.summarizeBooksForOrder(SummarizeShoppingCardCommand.of(user.getUsername(), session));
        session.setAttribute("summary", summarize);
        model.addAttribute("summary", summarize);
        return "authenticated/order_summary";
    }

    @PostMapping
    public String create(HttpSession session) {
        SummarizeShoppingCardDto summarize = (SummarizeShoppingCardDto) session.getAttribute("summary");
        orderFacade.create(summarize);
        session.setAttribute("summary", new SummarizeShoppingCardDto());
        session.setAttribute("shoppingCart", new LinkedList<Book>());
        return "authenticated/order_created";
    }
}
