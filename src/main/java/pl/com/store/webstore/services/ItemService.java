package pl.com.store.webstore.services;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Item;

import java.util.List;

public interface ItemService {

    Long addItem (ItemDto itemDto);

    List<Item> findAllItems();

    Item findItemById (Long id) throws Exception;

    Item updateItemById (Long id, ItemDto itemDto) throws Exception;

    String deleteItemById (Long id) throws Exception;

}
