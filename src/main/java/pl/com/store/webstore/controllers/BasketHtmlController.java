package pl.com.store.webstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BasketHtmlController {

 @GetMapping("/baskethtml")
    public String getBasketPanel(HttpServletRequest request, Model model){
     Object items = request.getSession().getAttribute("items");
     Object customerId = request.getSession().getAttribute("customerId");
     model.addAttribute("itemList",items);
     model.addAttribute("customer",customerId);
     return "/baskethtml";
 }
}
