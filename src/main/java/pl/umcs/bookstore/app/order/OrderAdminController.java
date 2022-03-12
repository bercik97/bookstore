package pl.umcs.bookstore.app.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.umcs.bookstore.app.order.domain.OrderFacade;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-panel/orders")
class OrderAdminController {

    private final OrderFacade facade;

    @GetMapping
    public String ordersPage(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("orders", facade.findAll(PageRequest.of(page, 10)));
        model.addAttribute("currentPage", page);
        return "authenticated/admin/orders";
    }

    @PostMapping("{id}")
    public String deleteById(@PathVariable long id) {
        facade.deleteById(id);
        return "redirect:/admin-panel/orders";
    }
}
