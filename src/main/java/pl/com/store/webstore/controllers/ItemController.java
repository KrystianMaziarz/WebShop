package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.entities.enums.Status;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.implementations.ItemServiceImp;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private ItemServiceImp service;
    private CustomerService customerService;

    public ItemController(ItemServiceImp service, CustomerService customerService) {
        this.service = service;
        this.customerService = customerService;
    }


    @PostMapping
    public void addItem(HttpServletResponse response, ItemDto itemDto) throws IOException {
        service.addItem(itemDto);
        response.sendRedirect("wellcome/admin");
    }

    @PostMapping("/buy")
    public void setBoughtItem(@ModelAttribute("item") ItemDto itemDto, HttpServletRequest request, HttpServletResponse response) {
        Principal currentUser = request.getUserPrincipal();
        Customer customer = customerService.findByEmail(currentUser.getName());
        Order order = new Order();
        order.setOrderPrice(itemDto.getPrice());
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(Status.PRZYJĘTO);
        Item item = service.setBoughtItem(itemDto, order);
        ItemDto dto = ItemMapper.mapToDto(item);
        OrderDto orderDto = dto.getOrders().get(0);
        String url = "/payment";
        request.getSession().setAttribute("itemDto", dto);
        request.getSession().setAttribute("orderDto", orderDto);
        response.setHeader("Location", url);
        response.setStatus(302);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAllItems() {
        return ResponseEntity.ok(service.findAllItems().stream()
                .map(ItemMapper::mapToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findItemById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(ItemMapper.mapToDto(service.findItemById(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItemById(@PathVariable Long id, @RequestBody ItemDto itemDto) throws Exception {
        return ResponseEntity.ok(ItemMapper.mapToDto(service.updateItemById(id, itemDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable Long id) throws Exception {
        service.deleteItemById(id);
        return ResponseEntity.ok("Deleted !");
    }


}
