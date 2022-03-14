package pl.umcs.bookstore.app.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/me")
    public String findMyOrders(Model model, Authentication authentication, @RequestParam(defaultValue = "0") int page) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        model.addAttribute("orders", orderFacade.findAllByUserEmail(user.getUsername(), PageRequest.of(page, 10)));
        model.addAttribute("currentPage", page);
        model.addAttribute("hasError", null);
        model.addAttribute("paymentFinished", null);
        return "authenticated/my_orders";
    }

    @GetMapping("/me/{id}")
    public String findMyOrderDetails(Model model, Authentication authentication, @PathVariable long id) {
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        model.addAttribute("order", orderFacade.findByUserEmailAndId(user.getUsername(), id));
        return "authenticated/my_order_details";
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
