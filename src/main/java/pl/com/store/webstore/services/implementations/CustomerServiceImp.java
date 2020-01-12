package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Sets;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.services.CustomerService;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

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
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        customer.setAddress(customerDto.getAddress());
        Authority authority = new Authority();
        authority.setAuthority("ROLE_CUSTOMER");
        authority.setCustomer(customer);
        customer.setAuthorities(Sets.newHashSet(authority));
        Customer persistedCustomer = repository.save(customer);
        return persistedCustomer.getId();
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(Long id) throws Exception {
        return repository.getOne(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public Customer updateCustomer(CustomerDto customerDto, AddressDto addressDto) throws Exception {
        Customer customer = repository.getOne(customerDto.getId());

        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());

        customer.getAddress().setCity(addressDto.getCity());
        customer.getAddress().setStreet(addressDto.getStreet());
        customer.getAddress().setNumber(addressDto.getNumber());
        customer.getAddress().setZipcode(addressDto.getZipcode());

        return customer;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        repository.deleteById(id);
    }

}
