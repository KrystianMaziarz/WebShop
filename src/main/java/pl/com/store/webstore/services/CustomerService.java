package pl.com.store.webstore.services;

import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Customer;

import java.util.List;


public interface CustomerService {

    Long addCustomer(CustomerDto customerDto) throws Exception;

    List<Customer> findAll();

    Customer findById(Long id) throws Exception;
    Customer findByEmail(String email);

    Customer updateCustomer(CustomerDto customerDto) throws Exception;

    void deleteById(Long id) throws Exception;
}
