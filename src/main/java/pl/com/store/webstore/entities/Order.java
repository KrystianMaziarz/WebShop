package pl.com.store.webstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.com.store.webstore.entities.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> items;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;

    private Status status;

    private BigDecimal orderPrice;

    public Order(Customer customer, List<Item> items, LocalDate orderDate) {
        this.customer = customer;
        this.items = items;
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getCustomer(), order.getCustomer()) &&
                Objects.equals(getItems(), order.getItems()) &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                getStatus() == order.getStatus() &&
                Objects.equals(getOrderPrice(), order.getOrderPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomer(), getItems(), getOrderDate(), getStatus(), getOrderPrice());
    }
}
