package pl.com.store.webstore.services.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

@Service
public class CustomerDetailServiceImp implements UserDetailsService {

    private CustomerRepository customerRepository;


    public CustomerDetailServiceImp(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            throw new UsernameNotFoundException("Invalid email or password");
        }
        Customer customer = customerRepository.findByEmail(email);

        return CustomerMapper.mapToCustomerDetails(customer);
    }
}
