package pl.com.store.webstore.services.implementations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.OrderService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;

    private OrderService service;

    @Before
    public void setUp() throws Exception {
        service=new OrderServiceImpl(orderRepository,customerRepository);
    }

    @Test
    public void testShouldAddOrderToRepository() {
        //given
        Customer customer=new Customer();
        customer.setId(7L);
        Order order=new Order();
        order.setCustomer(customer);
        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(order);
        Mockito.when(customerRepository.getOne(Mockito.any())).thenReturn(customer);
        //when
        OrderDto orderDto=new OrderDto();
        orderDto.setCustomerId(7L);
        service.addOrder(orderDto);
        //then
        Mockito.verify(orderRepository,Mockito.atLeastOnce()).save(order);
    }
}
