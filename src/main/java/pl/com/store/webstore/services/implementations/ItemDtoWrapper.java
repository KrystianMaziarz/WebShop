package pl.com.store.webstore.services.implementations;

import pl.com.store.webstore.controllers.dtos.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class ItemDtoWrapper {

    private List<ItemDto> items=new ArrayList<>();

    public void addItemDto(ItemDto dto) {
        this.items.add(dto);
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
