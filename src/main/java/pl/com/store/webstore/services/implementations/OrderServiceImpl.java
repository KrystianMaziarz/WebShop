package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.repositories.ItemRespository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.ItemService;
import pl.com.store.webstore.services.OrderService;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;

    private ItemRespository itemRespository;

    private CustomerRepository customerRepository;
    private ItemService service;

    public OrderServiceImpl(OrderRepository repository, ItemRespository itemRespository, CustomerRepository customerRepository, ItemService service) {
        this.repository = repository;
        this.itemRespository = itemRespository;
        this.customerRepository = customerRepository;
        this.service = service;
    }

    @Transactional
    @Override
    public Order addOrderDto(OrderDto orderDto) {
        Order order = new Order();
        Customer customer = customerRepository.getOne(orderDto.getCustomerId());
        order.setItems(orderDto.getItems().stream().map(itemDto -> ItemMapper.mapToItem(itemDto,customer)).collect(Collectors.toList()));
        order.setOrderPrice(orderDto.getOrderPrice());
        order.setOrderDate(orderDto.getOrderDate());
        Order persistedOrder = repository.save(order);
        return persistedOrder;
    }

    @Transactional
    @Override
    public Order addOrder(Order order, Item item) {

        Order persisted = repository.save(order);
        item.setOrders(Lists.newArrayList(persisted));
        persisted.setItems(Lists.newArrayList(item));
        return persisted;
    }

    @Transactional
    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteOrder(Order order){
        repository.delete(order);
    }

    @Transactional
    @Override
    public void deleteAll(List<Order> orders){
        repository.deleteAll(orders);
    }
}
