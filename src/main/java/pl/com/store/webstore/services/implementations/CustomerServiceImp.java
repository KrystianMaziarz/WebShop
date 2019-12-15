package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Sets;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.services.CustomerService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository repository;

    public CustomerServiceImp(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long addCustomer(CustomerDto customerDto) {
            Customer customer=new Customer();
            customer.setEmail(customerDto.getEmail());
            customer.setEnabled(true);
            customer.setPasswordExpirationDate(LocalDate.now().plusMonths(3));
            customer.setPassword(new BCryptPasswordEncoder().encode(customerDto.getPassword()));
            customer.setIslocked(false);
            customer.setAccountExpirationDate(LocalDate.now().plusMonths(6));
            customer.setFirstname(customerDto.getFirstname());
            customer.setLastname(customerDto.getLastname());
            customer.setAddress(customerDto.getAddress());
            Authority authority=new Authority();
            authority.setAuthority("ROLE_CUSTOMER");
            authority.setCustomer(customer);
            customer.setAuthorities(Sets.newHashSet(authority));
            Customer persistedCustomer = repository.save(customer);
            return persistedCustomer.getId();
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer findById(Long id) throws Exception {
        return null;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {

    }
}
