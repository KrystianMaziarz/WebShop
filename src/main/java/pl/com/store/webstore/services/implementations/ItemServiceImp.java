package pl.com.store.webstore.services.implementations;
import com.google.common.collect.Lists;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.store.webstore.controllers.dtos.ItemDto;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.ItemRespository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.ItemService;

import java.util.List;
@Service
public class ItemServiceImp implements ItemService {

    private ItemRespository respository;
    private OrderRepository orderRepository;

    public ItemServiceImp(ItemRespository respository, OrderRepository orderRepository) {
        this.respository = respository;
        this.orderRepository=orderRepository;
    }

    @Transactional
    @Override
    @Secured("ROLE_ADMIN")
    public Long addItem(ItemDto itemDto) {
        Item item = new Item();
        item.setCategory(itemDto.getCategory());
        item.setDescription(itemDto.getDescription());
        item.setName(itemDto.getName());
        item.setPhotoUrl(itemDto.getPhotoUrl());
        item.setPrice(itemDto.getPrice());
        Item persistedItem = respository.save(item);
        return persistedItem.getId();

    }
    @Transactional
    @Override
    public List<Item> findAllItems() {
        return respository.findAll();
    }
    @Transactional
    @Override
    public Item findItemById(Long id) throws Exception {
        if (id == null){
            throw new Exception("item not found");
        }
         Item item = respository.getOne(id);
        if (item == null) {
            throw new Exception("Cannot find item with specified id");
        }
        return item;

    }

    @Transactional
    @Override
    @Secured("ROLE_ADMIN")
    public Item updateItemById(Long id, ItemDto itemDto) throws Exception {
        if(id == null || itemDto == null ) {
            throw new Exception("item not found") ;
        }
        Item item = respository.getOne(id);
        if (item == null) {
            throw new Exception("Cannot find item with specified id");
        }
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        item.setPhotoUrl(itemDto.getPhotoUrl());
        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());
        return item;
    }

    @Transactional
    @Override
    public String deleteItemById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("item not found");
        }
        respository.deleteById(id);
        return "Deleted";
    }
    @Transactional
    @Override
    public Item setBoughtItem(ItemDto itemDto, Order order) {
        Item item = respository.getOne(itemDto.getId());
        if (item.getOrders()!=null){
        item.getOrders().add(order);}
        else{
        item.setOrders(Lists.newArrayList(order));}
        if (order.getItems()!=null){
        order.getItems().add(item);}
        else {
            order.setItems(Lists.newArrayList(item));
        }
        orderRepository.save(order);
        return item;
    }
}
