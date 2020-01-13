package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Item;

public class ItemMapper {

    public static ItemDto mapToDto (Item item) {
        ItemDto itemDto = new ItemDto();

        itemDto.setCategory(item.getCategory());
        itemDto.setDescription(item.getDescription());
        itemDto.setName(item.getName());
        itemDto.setPhotoUrl(item.getPhotoUrl());
        itemDto.setPrice(item.getPrice());

        return itemDto;
    }

}
