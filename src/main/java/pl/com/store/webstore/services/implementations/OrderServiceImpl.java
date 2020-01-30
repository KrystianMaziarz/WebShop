package pl.com.store.webstore.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.OrderService;
import pl.com.store.webstore.services.implementations.mappers.ItemMapper;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;
    private CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public Order addOrder(OrderDto orderDto) {
        Order order = new Order();
        Customer customer = customerRepository.getOne(orderDto.getCustomerId());
        if (orderDto.getItems()!=null){
        order.setItems(orderDto.getItems().stream().map(itemDto -> ItemMapper.mapToItem(itemDto, customer)).collect(Collectors.toList()));}
        order.setCustomer(customer);
        order.setOrderPrice(orderDto.getOrderPrice());
        order.setOrderDate(orderDto.getOrderDate());
        return repository.save(order);
    }
}
