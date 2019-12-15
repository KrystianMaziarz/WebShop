package pl.com.store.webstore.controllers.dtos;


import java.math.BigDecimal;

public class OrderDto {

    private Long customerId;

    private String orderDate;

    private BigDecimal orderPrice;


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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


    public String getCreateDate() {
        return orderDate;
    }

    public void setCreateDate(String createDate) {
        this.orderDate = createDate;
    }


}
