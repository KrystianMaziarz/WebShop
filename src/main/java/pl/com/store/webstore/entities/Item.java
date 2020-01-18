package pl.com.store.webstore.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String photoUrl;

    private BigDecimal price;
    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Order> orders;

    public Item(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getName(), item.getName()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getPhotoUrl(), item.getPhotoUrl()) &&
                Objects.equals(getPrice(), item.getPrice()) &&
                Objects.equals(getCategory(), item.getCategory()) &&
                Objects.equals(getOrders(), item.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getPhotoUrl(), getPrice(), getCategory(), getOrders());
    }
}
