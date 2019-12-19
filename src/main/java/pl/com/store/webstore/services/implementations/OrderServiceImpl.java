package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.repositories.ItemRespository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.ItemService;
import pl.com.store.webstore.services.OrderService;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;

    private ItemRespository itemRespository;

    private CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository repository, ItemRespository itemRespository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.itemRespository=itemRespository;
        this.customerRepository=customerRepository;
    }

    @Transactional
    @Override
    public Long addOrder(OrderDto orderDto) {
        Order order = new Order ();
        Customer customer=customerRepository.getOne(orderDto.getCustomerId());
        order.setCustomer(customer);
        order.setItems(orderDto.getItemsIds().stream().map(id ->itemRespository.getOne(id)).collect(Collectors.toList()));
        order.setOrderPrice(orderDto.getOrderPrice());
        order.setOrderDate(orderDto.getOrderDate());
        Order persistedOrder = repository.save(order);
        return persistedOrder.getId();
    }
}
