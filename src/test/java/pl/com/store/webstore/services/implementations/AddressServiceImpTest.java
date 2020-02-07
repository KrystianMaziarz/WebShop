package pl.com.store.webstore.services.implementations;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.AddressRepository;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.services.AddressService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImpTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;

    private AddressService service;

    @Before
    public void setUp() throws Exception {
        service = new AddressServiceImp(addressRepository, customerRepository);
    }

    @Test
    public void testShouldAddAddressAndCustomerToRepositories() {
        //given
        Mockito.when(addressRepository.save(Mockito.any())).thenReturn(new Address());
        Mockito.when(customerRepository.save(Mockito.any())).thenReturn(new Customer());
        CustomerDto customerDto = new CustomerDto();
        customerDto.setPassword("Pass1!");
        AddressDto addressDto = new AddressDto();
        //when
        service.addAddress(addressDto, customerDto);
        //then
        Mockito.verify(addressRepository, Mockito.atLeastOnce()).save(Mockito.any());
        Mockito.verify(customerRepository, Mockito.atLeastOnce()).save(Mockito.any());
    }

    @Test
    public void testShouldFindAllAddresses() {
        //given
        Mockito.when(addressRepository.findAll()).thenReturn(Lists.newArrayList(new Address()));
        List<Address> expected = Lists.newArrayList(new Address());
        //when
        List<Address> result = service.findAll();
        //then
        Mockito.verify(addressRepository, Mockito.atLeastOnce()).findAll();
        assertEquals(expected, result);
    }

    @Test
    public void testShouldFindAddressById() throws Exception {
        //given
        Address expected = new Address();
        expected.setId(1L);
        Mockito.when(addressRepository.getOne(1L)).thenReturn(expected);
        //when
        Address result = service.findById(1L);
        //then
        Mockito.verify(addressRepository, Mockito.atLeastOnce()).getOne(1L);
        assertEquals(expected, result);
    }

    @Test
    public void testShouldUpdateAddress() throws Exception {
        //given
        Mockito.when(addressRepository.getOne(Mockito.anyLong())).thenReturn(new Address());
        Address expected = new Address();
        expected.setCity("Piła");
        expected.setStreet("Cicha");
        expected.setNumber("8");
        expected.setZipcode("13-200");

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Piła");
        addressDto.setStreet("Cicha");
        addressDto.setNumber("8");
        addressDto.setZipcode("13-200");
        //when
        Address result = service.updateAddress(1L, addressDto);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void testShouldUpdateAddressButExpectedIsDifferentToResult() throws Exception {
        //given
        Mockito.when(addressRepository.getOne(Mockito.anyLong())).thenReturn(new Address());
        Address expected = new Address();
        expected.setCity("Piła");
        expected.setStreet("Cicha");
        expected.setNumber("8");
        expected.setZipcode("13-200");

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Chrząszczydrzeworzyce");
        addressDto.setStreet("Cicha");
        addressDto.setNumber("8");
        addressDto.setZipcode("13-200");
        //when
        Address result = service.updateAddress(1L, addressDto);
        //then
        assertNotEquals(expected, result);
    }

    @Test
    public void deleteById() throws Exception {
        //given
        Address address = new Address();
        address.setId(4L);
        Mockito.when(addressRepository.getOne(Mockito.anyLong())).thenReturn(address);
        //when
        service.deleteById(4L);
        //then
        Mockito.verify(addressRepository, Mockito.times(1)).delete(address);
    }
}
