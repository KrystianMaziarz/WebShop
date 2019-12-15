package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.services.OrderService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Long addOrder (@RequestBody OrderDto orderDto) {
        return service.addOrder (orderDto);
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
