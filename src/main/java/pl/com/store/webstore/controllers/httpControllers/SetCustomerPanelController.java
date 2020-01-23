package pl.com.store.webstore.controllers.httpControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.com.store.webstore.controllers.dtos.CustomerDto;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SetCustomerPanelController {

    @GetMapping({"/setcustomer"})
    public String setCustomerPanel(HttpServletRequest httpServletRequest, Model model) {
        CustomerDto customerDto = (CustomerDto) httpServletRequest.getSession().getAttribute("customer");
        model.addAttribute("customer", customerDto);
        return "/setcustomer";
    }
}
