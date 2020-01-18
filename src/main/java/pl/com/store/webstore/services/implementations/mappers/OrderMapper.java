package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Order;

public class OrderMapper {

    public static OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setOrderPrice(order.getOrderPrice());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public static Order mapToOrder(OrderDto orderDto) {
        // TODO: 17/01/2020 do implementacji jeżeli będzie potrzeba
        return null;
    }
}
