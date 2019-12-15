package pl.com.store.webstore.controllers.dtos;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {

    private Long customerId;

    private LocalDate orderDate;

    private BigDecimal orderPrice;

    private List<Long> itemsIds;

    public List<Long> getItemsIds() {
        return itemsIds;
    }

    public void setItemsIds(List<Long> itemsIds) {
        this.itemsIds = itemsIds;
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
