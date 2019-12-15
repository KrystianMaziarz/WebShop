package pl.com.store.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.store.webstore.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
