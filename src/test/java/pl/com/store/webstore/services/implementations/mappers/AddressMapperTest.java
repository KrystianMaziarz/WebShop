package pl.com.store.webstore.services.implementations.mappers;

import org.junit.Test;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.entities.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AddressMapperTest {

    @Test
    public void shouldMapToDto() {
        //given
        Address address = new Address();
        Customer customer = new Customer();
        customer.setId(2L);
        address.setCity("Lublin");
        address.setStreet("Cicha");
        address.setNumber("234");
        address.setZipcode("20-950");
        address.setCustomer(customer);

        AddressDto expected = new AddressDto();
        expected.setCity("Lublin");
        expected.setStreet("Cicha");
        expected.setNumber("234");
        expected.setZipcode("20-950");
        expected.setCustomerId(2L);
        //when
        AddressDto result = AddressMapper.mapToDto(address);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void shouldMapToDtoButExpectedIsWrong() {
        //given
        Address address = new Address();
        Customer customer = new Customer();
        customer.setId(2L);
        address.setCity("Lublin");
        address.setStreet("Cicha");
        address.setNumber("234");
        address.setZipcode("20-950");
        address.setCustomer(customer);

        AddressDto expected = new AddressDto();
        expected.setCity("Lalalalalala");
        expected.setStreet("Cicha");
        expected.setNumber("234");
        expected.setZipcode("20-950");
        expected.setCustomerId(2L);
        //when
        AddressDto result = AddressMapper.mapToDto(address);
        //then
        assertNotEquals(expected, result);
    }
}
