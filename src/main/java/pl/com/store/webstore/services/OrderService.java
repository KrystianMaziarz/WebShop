package pl.com.store.webstore.services;

import pl.com.store.webstore.controllers.dtos.OrderDto;

public interface OrderService {


    Long addOrder(OrderDto orderDto);
}
