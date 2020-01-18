package pl.com.store.webstore.controllers.dtos;

import pl.com.store.webstore.entities.enums.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto implements Serializable {

    private Long id;


    private Long customerId;

    private LocalDate orderDate;

    private BigDecimal orderPrice;

    private List<ItemDto> items;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> itemsIds) {
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


}
