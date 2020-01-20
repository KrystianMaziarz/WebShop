package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentPanelController {

    @GetMapping({"/payment"})
    public String getPaymentPanel(HttpServletRequest httpServletRequest, Model model) {
        ItemDto itemDto = (ItemDto) httpServletRequest.getSession().getAttribute("itemDto");
        OrderDto orderDto = (OrderDto) httpServletRequest.getSession().getAttribute("orderDto");
        model.addAttribute("item", itemDto);
        model.addAttribute("orderDto", orderDto);
        return "/payment";
    }
}
