package pl.com.store.webstore.services.implementations.mappers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.security.CustomCustomerDetails;

import java.time.LocalDate;

public class CustomerMapper {

    public static UserDetails mapToCustomerDetails(Customer customer){

        if (customer==null){
            return null;
        }
        CustomCustomerDetails customerDetails=new CustomCustomerDetails();
        customerDetails.setEmail(customer.getEmail());
        customerDetails.setPassword(customer.getPassword());
        customerDetails.setFirstname(customer.getFirstname());
        customerDetails.setLastname(customer.getLastname());
        customerDetails.setAuthorities(customer.getAuthoritySet());
        customerDetails.setId(customer.getId());
        customerDetails.setOrders(customer.getOrders());
        customerDetails.setAccountExpirationDate(customer.getAccountExpirationDate());
        customerDetails.setIslocked(customer.getIslocked());
        customerDetails.setPasswordExpirationDate(customer.getPasswordExpirationDate());
        customerDetails.setIsEnabled(customer.getIsEnabled());
        return customerDetails;
    }

    public static CustomerDto mapToDto(Customer customer){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setLastname(customer.getLastname());
        customerDto.setEmail(customer.getEmail());
        AddressDto addressDto = AddressMapper.mapToDto(customer.getAddress());
        addressDto.setCustomerId(customer.getId());
        customerDto.setAddressDto(addressDto);
        customerDto.setPassword(customer.getPassword());

        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setIsEnabled(true);
        customer.setPasswordExpirationDate(LocalDate.now().plusMonths(3));
        customer.setPassword(new BCryptPasswordEncoder().encode(customerDto.getPassword()));
        customer.setIslocked(false);
        customer.setAccountExpirationDate(LocalDate.now().plusMonths(6));
        customer.setFirstname(customerDto.getFirstname());
        customer.setLastname(customerDto.getLastname());

        return customer;
    }
}
