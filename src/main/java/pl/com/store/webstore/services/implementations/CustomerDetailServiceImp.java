package pl.com.store.webstore.services.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.sda.tools.domain.User;
import pl.com.sda.tools.repositories.UserRepository;
import pl.com.sda.tools.services.implementations.mappers.UserMapper;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

@Service
public class CustomerDetailServiceImp implements UserDetailsService {

    private CustomerRepository customerRepository;


    public CustomerDetailServiceImp(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String customername) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUserName(customername);
        if (customername == null) {
            throw new UsernameNotFoundException("Invalid customername or password");
        }

        return CustomerMapper.mapToCustomerDetails(customer);
    }
}
