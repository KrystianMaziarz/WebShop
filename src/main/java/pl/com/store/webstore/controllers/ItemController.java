package pl.com.store.webstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.services.implementations.ItemServiceImp;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private ItemServiceImp service;

    public ItemController(ItemServiceImp service) {
        this.service = service;
    }


    @PostMapping
    public void addItem (HttpServletResponse response,ItemDto itemDto) throws IOException {
         service.addItem (itemDto);
         response.sendRedirect("wellcome/admin");
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
    public ResponseEntity<ItemDto> updateItemById (@PathVariable Long id, @RequestBody ItemDto itemDto) throws Exception {
        return ResponseEntity.ok(ItemMapper.mapToDto(service.updateItemById(id, itemDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById (@PathVariable Long id) throws Exception {
        service.deleteItemById(id);
        return ResponseEntity.ok("Deleted !");
    }


}
