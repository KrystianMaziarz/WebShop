package pl.com.store.webstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Data
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Order> orders;

    public Item(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(getId(), item.getId()) &&
                Objects.equals(getName(), item.getName()) &&
                Objects.equals(getDescription(), item.getDescription()) &&
                Objects.equals(getPhotoUrl(), item.getPhotoUrl()) &&
                Objects.equals(getPrice(), item.getPrice()) &&
                Objects.equals(getCategory(), item.getCategory()) &&
                Objects.equals(getOrders(), item.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPhotoUrl(), getPrice(), getCategory(), getOrders());
    }
}
