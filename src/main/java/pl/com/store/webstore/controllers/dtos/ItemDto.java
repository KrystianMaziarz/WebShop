package pl.com.store.webstore.controllers.dtos;

import pl.com.store.webstore.entities.Category;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ItemDto implements Serializable {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private Category category;

    private String photoUrl;

    private List<OrderDto>orders;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDto)) return false;
        ItemDto itemDto = (ItemDto) o;
        return Objects.equals(getName(), itemDto.getName()) &&
                Objects.equals(getPrice(), itemDto.getPrice()) &&
                Objects.equals(getDescription(), itemDto.getDescription()) &&
                Objects.equals(getCategory(), itemDto.getCategory()) &&
                Objects.equals(getPhotoUrl(), itemDto.getPhotoUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getDescription(), getCategory(), getPhotoUrl());
    }
}
