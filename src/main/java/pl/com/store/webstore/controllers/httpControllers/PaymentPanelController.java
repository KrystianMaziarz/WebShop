package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.services.implementations.ItemDtoWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentPanelController {

    @GetMapping({"/payment"})
    public String getPaymentPanel(HttpServletRequest httpServletRequest, Model model) {
        ItemDto itemDto = (ItemDto) httpServletRequest.getSession().getAttribute("itemDto");
        OrderDto orderDto = (OrderDto) httpServletRequest.getSession().getAttribute("orderDto");
        List<ItemDto> items=new ArrayList<>();
        if (httpServletRequest.getSession().getServletContext().getContextPath().equals("/basket")){
        ItemDtoWrapper wrapper =(ItemDtoWrapper) httpServletRequest.getSession().getAttribute("itemz");
         items = wrapper.getItems();}
        boolean basket = (boolean)httpServletRequest.getSession().getAttribute("basket");
        model.addAttribute("itemDto", itemDto);
        model.addAttribute("basket",basket);
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("items",items);
        return "/payment";
    }
}
