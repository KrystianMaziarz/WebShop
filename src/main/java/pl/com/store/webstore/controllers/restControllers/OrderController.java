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
    // TODO: 13/01/2020 do poprawy metoda i frontend 
    public ResponseEntity<OrderDto> addOrder(@ModelAttribute("orderDto") OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.mapToDto(service.addOrderDto(orderDto)));
    }

    //TODO 15.12.2019
  /*  @GetMapping
    public ResponseEntity<List<ItemDto>> findAllOrders() {
        return ResponseEntity.ok(service.findAllOrders().stream()
                .map(OrderMapper::mapOrderToDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findOrderById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(OrderMapper.mapToDto(service.findOrderById(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateOrderById (@PathVariable Long id, @RequestBody ItemDto itemDto) throws Exception {
        return ResponseEntity.ok(OrderMapper.mapToDto(service.updateItemById(id, itemDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById (@PathVariable Long id) throws Exception {
        service.deleteOrderById(id);
        return ResponseEntity.ok("Deleted !");
    }
*/

}
