package pl.com.store.webstore.controllers.httpControllers;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.services.implementations.ItemDtoWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentPanelController {

    @GetMapping({"/payment"})
    public String getPaymentPanel(HttpServletRequest httpServletRequest, Model model) {
        ItemDto itemDto = (ItemDto) httpServletRequest.getSession().getAttribute("itemDto");
        OrderDto orderDto = (OrderDto) httpServletRequest.getSession().getAttribute("orderDto");
        ItemDtoWrapper itemWrap =(ItemDtoWrapper) httpServletRequest.getSession().getAttribute("itemz");
        List<ItemDto>items;
        if (itemWrap!=null){
            items=itemWrap.getItems();
        }else {
            items=Lists.newArrayList();
        }

        boolean basket = (boolean)httpServletRequest.getSession().getAttribute("basket");
        model.addAttribute("itemDto", itemDto);
        model.addAttribute("basket",basket);
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("items",items);
        return "/payment";
    }
}
