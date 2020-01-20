package pl.com.store.webstore.controllers.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.services.Basket;
import pl.com.store.webstore.services.BasketService;
import pl.com.store.webstore.services.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private BasketService basketService;
    private CustomerService customerService;

    public BasketController(BasketService basketService, CustomerService customerService) {
        this.basketService = basketService;
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public void addToBasket(@ModelAttribute("item") ItemDto itemDto, HttpServletRequest request, HttpServletResponse response) {
        // TODO: 17/01/2020 poprawic! nie zapisuje itemDto w serwisie
        String email = request.getUserPrincipal().getName();
        Customer customer = customerService.findByEmail(email);
        Basket basket = basketService.addToBasket(customer.getId(), itemDto);
        long itemsAtBasket = basket.getItems().size();

        String url = "/wellcome/logged";
        request.getSession().setAttribute("count", itemsAtBasket);
        response.setHeader("Location", url);
        response.setStatus(302);
    }

    @GetMapping
    public void showBasket(@ModelAttribute("basket") Basket basket, HttpServletRequest request,HttpServletResponse response) {

        Basket foundBasket = basketService.getBasket(basket.getCustomerId());
        List<ItemDto> items = foundBasket.getItems();
        Long customerId = foundBasket.getCustomerId();
        String url="/baskethtml";
        request.getSession().setAttribute("items",items);
        request.getSession().setAttribute("customerId",customerId);
        response.setHeader("Location",url);
        response.setStatus(302);
    }
}
