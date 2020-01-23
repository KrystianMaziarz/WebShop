package pl.com.store.webstore.services.implementations.mappers;

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.security.CustomCustomerDetails;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerMapperTest {

    @Test
    public void shouldMapToCustomerDetails() {
        //given
        Customer customer = new Customer();
        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");
        customer.setAuthorities(Sets.newHashSet(authority));
        customer.setEmail("cust@mail.pl");
        customer.setPassword("Pass1!");
        customer.setFirstname("Jan");
        customer.setLastname("Nowak");
        customer.setId(1L);
        Order order = new Order();
        customer.setOrders(Lists.newArrayList(order));
        customer.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        customer.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        customer.setIsEnabled(true);
        customer.setIslocked(false);

        CustomCustomerDetails expected = new CustomCustomerDetails();
        expected.setAuthorities(Sets.newHashSet(authority));
        expected.setEmail("cust@mail.pl");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        expected.setId(1L);
        expected.setOrders(Lists.newArrayList(order));
        expected.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        expected.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIsEnabled(true);
        expected.setIslocked(false);
        //when
        UserDetails result = CustomerMapper.mapToCustomerDetails(customer);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToCustomerDetailsButExpectedObjectIsWrong() {
        //given
        Customer customer = new Customer();
        Authority authority = new Authority();
        authority.setAuthority("ROLE_ADMIN");
        customer.setAuthorities(Sets.newHashSet(authority));
        customer.setEmail("cust@mail.pl");
        customer.setPassword("Pass1!");
        customer.setFirstname("Jan");
        customer.setLastname("Nowak");
        customer.setId(1L);
        Order order = new Order();
        customer.setOrders(Lists.newArrayList(order));
        customer.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        customer.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        customer.setIsEnabled(true);
        customer.setIslocked(false);

        CustomCustomerDetails expected = new CustomCustomerDetails();
        expected.setAuthorities(Sets.newHashSet(authority));
        expected.setEmail("blablablablablabla");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        expected.setId(1L);
        expected.setOrders(Lists.newArrayList(order));
        expected.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        expected.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIsEnabled(true);
        expected.setIslocked(false);
        //when
        UserDetails result = CustomerMapper.mapToCustomerDetails(customer);
        //then
        assertNotEquals(expected, result);
    }


    @Test
    public void shouldMapToDto() {
        //given
        Customer customer = new Customer();
        Address address = new Address();
        address.setCity("Piła");
        address.setStreet("Cicha");
        address.setNumber("7");
        address.setZipcode("11-200");
        customer.setEmail("cust@mail.pl");
        customer.setPassword("Pass1!");
        customer.setFirstname("Jan");
        customer.setLastname("Nowak");
        customer.setId(1L);
        customer.setAddress(address);

        CustomerDto expected = new CustomerDto();
        expected.setEmail("cust@mail.pl");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        expected.setId(1L);
        expected.setAddressDto(AddressMapper.mapToDto(address));
        //when
        CustomerDto result = CustomerMapper.mapToDto(customer);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToDtoWhenAddressIsNull() {
        //given
        Customer customer = new Customer();
        customer.setEmail("cust@mail.pl");
        customer.setPassword("Pass1!");
        customer.setFirstname("Jan");
        customer.setLastname("Nowak");
        customer.setId(1L);
        customer.setAddress(null);

        CustomerDto expected = new CustomerDto();
        expected.setEmail("cust@mail.pl");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        expected.setId(1L);
        expected.setAddressDto(null);
        //when
        CustomerDto result = CustomerMapper.mapToDto(customer);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToDtoButExpectedObjectIsWrong() {
        //given
        Customer customer = new Customer();
        Address address = new Address();
        address.setCity("Piła");
        address.setStreet("Cicha");
        address.setNumber("7");
        address.setZipcode("11-200");
        customer.setEmail("cust@mail.pl");
        customer.setPassword("Pass1!");
        customer.setFirstname("Jan");
        customer.setLastname("Nowak");
        customer.setId(1L);
        customer.setAddress(address);

        CustomerDto expected = new CustomerDto();
        expected.setEmail("blabla");
        expected.setPassword("Pass1!");
        expected.setFirstname("ciaoooooooo");
        expected.setLastname("Nowak");
        expected.setId(1L);
        expected.setAddressDto(AddressMapper.mapToDto(address));
        //when
        CustomerDto result = CustomerMapper.mapToDto(customer);
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void shouldMapToCustomer() {
        //given
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("jan@mail.pl");
        customerDto.setPassword("Pass1!");
        customerDto.setFirstname("Jan");
        customerDto.setLastname("Nowak");
        //when
        Customer result = CustomerMapper.mapToCustomer(customerDto);

        Customer expected = new Customer();
        expected.setEmail("jan@mail.pl");
        expected.setIsEnabled(true);
        expected.setPasswordExpirationDate(LocalDate.now().plusMonths(3));
        expected.setPassword(result.getPassword());
        expected.setIslocked(false);
        expected.setAccountExpirationDate(LocalDate.now().plusMonths(6));
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        //then
        assertEquals(expected, result);
    }
}
