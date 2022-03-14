package pl.umcs.bookstore.app.payu;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pl.umcs.bookstore.app.order.domain.OrderFacade;
import pl.umcs.bookstore.app.order.domain.OrderStatus;
import pl.umcs.bookstore.app.order.domain.command.UpdateOrderStatusCommand;
import pl.umcs.bookstore.app.order.domain.dto.OrderDetailsDto;
import pl.umcs.bookstore.app.payu.domain.command.CreatePayUPaymentCommand;
import pl.umcs.bookstore.app.payu.domain.model.PayUOrderResponse;
import pl.umcs.bookstore.app.payu.domain.service.PayUAPIService;
import pl.umcs.bookstore.app.security.model.CustomUserDetails;
import pl.umcs.bookstore.app.user.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payu")
class PayUController {

    private final PayUAPIService service;
    private final OrderFacade orderFacade;

    private static final String SUCCESS_STATUS_CODE = "SUCCESS";

    @PostMapping("{id}")
    public RedirectView makePayment(@PathVariable long id, Authentication authentication, HttpSession httpSession, HttpServletRequest request) {
        OrderDetailsDto order = orderFacade.findById(id);
        User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        PayUOrderResponse response = service.makePayment(CreatePayUPaymentCommand.of(order, user.getUsername(), request));
        if (!SUCCESS_STATUS_CODE.equals(response.getStatus().getStatusCode())) {
            throw new RuntimeException("Something went wrong while making payment!");
        }
        httpSession.setAttribute("orderId", id);
        return new RedirectView(response.getRedirectUri());
    }

    @GetMapping("/callback")
    public String handlePaymentCallback(@RequestParam Optional<String> error, HttpSession httpSession) {
        error.ifPresentOrElse(System.out::println, () -> {
            long orderId = (long) httpSession.getAttribute("orderId");
            httpSession.removeAttribute("orderId");
            orderFacade.updateStatus(UpdateOrderStatusCommand.of(orderId, OrderStatus.PAID));
        });
        return "redirect:/orders/me";
    }
}
