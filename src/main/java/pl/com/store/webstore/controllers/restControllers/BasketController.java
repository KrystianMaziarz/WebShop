package pl.com.store.webstore.controllers.restControllers;

import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.entities.enums.Status;
import pl.com.store.webstore.services.Basket;
import pl.com.store.webstore.services.BasketService;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.ItemService;
import pl.com.store.webstore.services.implementations.ItemDtoWrapper;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;
import pl.com.store.webstore.services.implementations.mappers.OrderMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private BasketService basketService;
    private CustomerService customerService;
    private ItemService itemService;
    private List<ItemDto> items;

    public BasketController(BasketService basketService, CustomerService customerService, ItemService itemService) {
        this.basketService = basketService;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    private static double applyAsDouble(ItemDto itemDto) {
        return itemDto.getPrice().doubleValue();
    }

    @PostMapping("/add")
    public void addToBasket(@ModelAttribute("item") ItemDto itemDto, HttpServletRequest request, HttpServletResponse response) {
        String email = request.getUserPrincipal().getName();
        Customer customer = customerService.findByEmail(email);
        Basket basket = basketService.addToBasket(customer.getId(), itemDto);
        long itemsAtBasket = basket.getItems().size();

        String url = "/wellcome/logged";
        request.getSession().setAttribute("count", itemsAtBasket);
        setResponse(response, url);
    }

    private void setResponse(HttpServletResponse response, String url) {
        response.setHeader("Location", url);
        response.setStatus(302);
    }

    @GetMapping
    public void showBasket(@ModelAttribute("basket") Basket basket, HttpServletRequest request, HttpServletResponse response) {

        Basket foundBasket = basketService.getBasket(basket.getCustomerId());
        items = foundBasket.getItems();
        ItemDtoWrapper wrapper = new ItemDtoWrapper();
        items.forEach(wrapper::addItemDto);
        Long customerId = foundBasket.getCustomerId();
        String url = "/baskethtml";
        request.getSession().setAttribute("wrapper", wrapper);
        request.getSession().setAttribute("customerId", customerId);
        setResponse(response, url);

    }

    @PostMapping("/buy")
    public void setBoughtBasket(HttpServletRequest request, HttpServletResponse response) {
        ItemDtoWrapper wrapperToSend = new ItemDtoWrapper();
        Principal currentUser = request.getUserPrincipal();
        Customer customer = customerService.findByEmail(currentUser.getName());

        Order order = getOrder(items, customer);
        List<Item> itemz = getListOfItems(items, order);
        List<ItemDto> dtos = itemz.stream().map(ItemMapper::mapToDto).collect(Collectors.toList());
        dtos.forEach(wrapperToSend::addItemDto);

        OrderDto orderDto = OrderMapper.mapToDto(order);

        String url = "/payment";
        request.getSession().setAttribute("basket", true);
        request.getSession().setAttribute("itemz", wrapperToSend);
        request.getSession().setAttribute("orderDto", orderDto);
        setResponse(response, url);
    }

    private List<Item> getListOfItems(List<ItemDto> itemDtos, Order order) {
        return itemDtos.stream().map(itemDto -> itemService.setBoughtItem(itemDto, order)).collect(Collectors.toList());
    }

    private Order getOrder(List<ItemDto> itemDtos, Customer customer) {
        Order order = new Order();
        order.setOrderPrice(BigDecimal.valueOf(getSum(itemDtos)));
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(Status.PRZYJĘTO);
        return order;
    }

    private double getSum(List<ItemDto> itemDtos) {
        return itemDtos.stream()
                .mapToDouble(BasketController::applyAsDouble)
                .sum();
    }
}
