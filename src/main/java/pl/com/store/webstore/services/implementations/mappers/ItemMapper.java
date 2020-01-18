package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Item;

import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemDto mapToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setCategory(item.getCategory());
        itemDto.setDescription(item.getDescription());
        itemDto.setName(item.getName());
        itemDto.setPhotoUrl(item.getPhotoUrl());
        itemDto.setPrice(item.getPrice());

        itemDto.setOrders(item.getOrders().stream().map(OrderMapper::mapToDto).collect(Collectors.toList()));

        return itemDto;
    }

    public static Item mapToItem(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setCategory(itemDto.getCategory());
        item.setPhotoUrl(itemDto.getPhotoUrl());
        item.setOrders(itemDto.getOrders().stream().map(OrderMapper::mapToOrder).collect(Collectors.toList()));

        return item;
    }
}
