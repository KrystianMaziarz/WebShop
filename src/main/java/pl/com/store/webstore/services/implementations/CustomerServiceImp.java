package pl.com.store.webstore.services.implementations;

import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.services.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService {
    @Override
    public Long addCustomer(CustomerDto customerDto) {
        return null;
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
