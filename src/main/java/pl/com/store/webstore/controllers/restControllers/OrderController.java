package pl.com.store.webstore.controllers.restControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.services.OrderService;
import pl.com.store.webstore.services.implementations.mappers.OrderMapper;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;

    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@ModelAttribute("orderDto") OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.mapToDto(service.addOrder(orderDto)));
    }
}
