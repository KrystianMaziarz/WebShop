package pl.com.store.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.store.webstore.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
