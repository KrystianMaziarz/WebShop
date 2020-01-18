package pl.com.store.webstore.services;


import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;

import java.util.List;
@Service
public class Basket {

    private List<ItemDto>items;

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items=items;
    }
}
