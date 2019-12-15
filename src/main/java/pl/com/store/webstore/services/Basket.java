package pl.com.store.webstore.services;


import pl.com.store.webstore.entities.Order;

import java.util.List;

public class Basket {

    private List<Order>orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
