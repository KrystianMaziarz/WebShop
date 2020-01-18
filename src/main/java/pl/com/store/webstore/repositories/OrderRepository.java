package pl.com.store.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.store.webstore.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
