package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        if (order.getCustomer()!=null){
        dto.setCustomerId(order.getCustomer().getId());}
        dto.setOrderPrice(order.getOrderPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public static Order mapToOrder(OrderDto orderDto,Customer customer) {
       Order order=new Order();
       order.setId(orderDto.getId());
       order.setCustomer(customer);
       order.setItems(orderDto.getItems().stream().map(itemDto -> ItemMapper.mapToItem(itemDto,customer)).collect(Collectors.toList()));
       order.setStatus(orderDto.getStatus());
       order.setOrderDate(orderDto.getOrderDate());
       order.setOrderPrice(orderDto.getOrderPrice());
        return order;
    }
}
