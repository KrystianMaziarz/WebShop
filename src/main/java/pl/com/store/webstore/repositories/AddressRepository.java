package pl.com.store.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.store.webstore.entities.Address;

public interface AddressRepository extends JpaRepository<Address , Long> {
}
