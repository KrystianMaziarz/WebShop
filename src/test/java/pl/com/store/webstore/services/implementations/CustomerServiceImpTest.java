package pl.com.store.webstore.services.implementations;

import org.assertj.core.util.Lists;
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
import pl.com.store.webstore.repositories.OrderRepository;
import pl.com.store.webstore.services.CustomerService;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImpTest {

    @Mock
    private CustomerRepository repository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private OrderRepository orderRepository;

    private CustomerService service;

    @org.junit.Before
    public void setUp() throws Exception {
        service = new CustomerServiceImp(repository, addressRepository, orderRepository);
    }

    @org.junit.Test
    public void testShouldAddCustomer() throws Exception {
        //given
        Mockito.when(repository.save(Mockito.any())).thenReturn(new Customer());
        //when
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("customer@wp.pl");
        customerDto.setPassword("Pass1!");
        service.addCustomer(customerDto);
        //then
        Mockito.verify(repository, Mockito.atLeastOnce()).save(Mockito.any());
    }

    @org.junit.Test
    public void shouldFindAllCustomers() {
        //given
        Mockito.when(repository.findAll()).thenReturn(Lists.newArrayList(new Customer()));
        List<Customer> expected = Lists.newArrayList(new Customer());
        //when
        List<Customer> result = service.findAll();
        //then
        Mockito.verify(repository, Mockito.atLeastOnce()).findAll();
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void shouldFindCustomerById() throws Exception {
        //given
        Customer expected = new Customer();
        expected.setId(1L);
        Mockito.when(repository.getOne(1L)).thenReturn(expected);
        //when
        Customer result = service.findById(1L);
        //then
        Mockito.verify(repository, Mockito.atLeastOnce()).getOne(1L);
        assertEquals(expected, result);
    }

    @org.junit.Test
    public void shouldFindCustomerByEmail() {
        //given
        Customer customer = new Customer();
        customer.setEmail("customer@wp.pl");
        Mockito.when(repository.findByEmail("customer@wp.pl")).thenReturn(customer);
        //when
        service.findByEmail("customer@wp.pl");
        //then
        Mockito.verify(repository, Mockito.atLeastOnce()).findByEmail("customer@wp.pl");
    }

    @org.junit.Test
    public void shouldUpdateCustomer() throws Exception {
        //given
        Customer customer = new Customer();
        Address address = new Address();
        customer.setAddress(address);
        Mockito.when(repository.getOne(Mockito.any())).thenReturn(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("customer@wp.pl");
        customerDto.setPassword("Pass1!");
        customerDto.setFirstname("Jan");
        customerDto.setLastname("Nowak");
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Piła");
        addressDto.setStreet("Cicha");
        addressDto.setNumber("8");
        addressDto.setZipcode("13-200");
        customerDto.setAddressDto(addressDto);

        Address expectedAddress = new Address();
        expectedAddress.setCity("Piła");
        expectedAddress.setStreet("Cicha");
        expectedAddress.setNumber("8");
        expectedAddress.setZipcode("13-200");
        Customer expectedCustomer = new Customer();
        expectedCustomer.setAddress(expectedAddress);
        expectedCustomer.setEmail("customer@wp.pl");
        expectedCustomer.setPassword("Pass1!");
        expectedCustomer.setFirstname("Jan");
        expectedCustomer.setLastname("Nowak");
        //when
        Customer persistedCustomer = service.updateCustomer(customerDto);
        //then
        assertEquals(expectedCustomer, persistedCustomer);
    }

    @org.junit.Test
    public void shouldDeleteById() throws Exception {
        //when
        service.deleteById(1L);
        //then
        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }
}
