package pl.com.store.webstore.services.implementations.mappers;

import org.springframework.security.core.userdetails.UserDetails;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.security.CustomCustomerDetails;

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
        customerDto.setFirstname(customer.getFirstname());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPassword(customer.getPassword());

        return customerDto;
    }
}
