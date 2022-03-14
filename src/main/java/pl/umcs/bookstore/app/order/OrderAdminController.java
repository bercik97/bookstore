package pl.umcs.bookstore.app.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.umcs.bookstore.app.order.domain.OrderFacade;
import pl.umcs.bookstore.app.order.domain.OrderStatus;
import pl.umcs.bookstore.app.order.domain.command.UpdateOrderStatusCommand;
import pl.umcs.bookstore.app.order.domain.dto.UpdateOrderStatusDto;

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

    @GetMapping("/edit/{id}")
    public String orderEditPage(Model model, @PathVariable long id) {
        model.addAttribute("order", facade.findById(id));
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("updateOrderStatusDto", new UpdateOrderStatusDto());
        return "authenticated/admin/edit_order";
    }

    @PostMapping("edit/status/{id}")
    public String editStatus(@ModelAttribute("updateOrderStatusDto") UpdateOrderStatusDto dto, @PathVariable long id) {
        facade.updateStatus(UpdateOrderStatusCommand.of(id, dto.getStatus()));
        return "redirect:/admin-panel/orders/edit/{id}";
    }

    @PostMapping("{id}")
    public String deleteById(@PathVariable long id) {
        facade.deleteById(id);
        return "redirect:/admin-panel/orders";
    }
}
