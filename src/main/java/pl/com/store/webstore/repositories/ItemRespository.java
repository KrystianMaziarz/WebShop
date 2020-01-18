package pl.com.store.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.store.webstore.entities.Item;

public interface ItemRespository extends JpaRepository<Item, Long> {
}
