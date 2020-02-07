package pl.com.store.webstore.services;

import pl.com.store.webstore.controllers.dtos.OrderDto;
import pl.com.store.webstore.entities.Item;
import pl.com.store.webstore.entities.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(OrderDto orderDto);
}
