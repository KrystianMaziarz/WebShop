package pl.com.store.webstore.services.basket;


import pl.com.store.webstore.controllers.dtos.ItemDto;

import java.util.List;
import java.util.Objects;

public class Basket {

    private List<ItemDto>items;

    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public List<ItemDto> getItems() {
        return items;
    }
    public void setItems(List<ItemDto> items) {
        this.items=items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;
        Basket basket = (Basket) o;
        return Objects.equals(getItems(), basket.getItems()) &&
                Objects.equals(getCustomerId(), basket.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems(), getCustomerId());
    }
}
