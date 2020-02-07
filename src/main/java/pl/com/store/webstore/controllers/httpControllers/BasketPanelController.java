package pl.com.store.webstore.controllers.httpControllers;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.services.implementations.ItemDtoWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BasketPanelController {

    @GetMapping("/baskethtml")
    public String getBasketPanel(HttpServletRequest request, Model model) {
        ItemDtoWrapper toUnwrap = (ItemDtoWrapper) request.getSession().getAttribute("wrapper");
        List<ItemDto> items;
        if (toUnwrap != null) {
            items = toUnwrap.getItems();
        } else {
            items = Lists.newArrayList();
        }

        Object customerId = request.getSession().getAttribute("customerId");
        model.addAttribute("items", items);
        model.addAttribute("customer", customerId);
        return "/baskethtml";
    }
}
