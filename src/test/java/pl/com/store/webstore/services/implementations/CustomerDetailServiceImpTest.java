package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Order;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.security.CustomCustomerDetails;

import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class CustomerDetailServiceImpTest {

    @Mock
    private CustomerRepository repository;

    private CustomerDetailServiceImp service;
    @Before
    public void setUp() throws Exception {
        service=new CustomerDetailServiceImp(repository);
    }

    @Test
    public void testShouldLoadUserByUsername() {
        //given
        CustomCustomerDetails expected=new CustomCustomerDetails();
        expected.setEmail("any@mail.com");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        Authority authority=new Authority();
        authority.setAuthority("ROLE_USER");
        expected.setAuthorities(Sets.newHashSet(authority));
        expected.setId(2L);
        expected.setOrders(Lists.newArrayList(new Order()));
        expected.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIslocked(false);
        expected.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIsEnabled(true);
        Mockito.when(repository.findByEmail("any@mail.com")).thenReturn(expected);
        //when
        UserDetails result = service.loadUserByUsername("any@mail.com");
        //then
        assertEquals(expected,result);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testShouldThrowUserNameNotFoundException() {
        //given
        CustomCustomerDetails expected=new CustomCustomerDetails();
        expected.setEmail("any@mail.com");
        expected.setPassword("Pass1!");
        expected.setFirstname("Jan");
        expected.setLastname("Nowak");
        Authority authority=new Authority();
        authority.setAuthority("ROLE_USER");
        expected.setAuthorities(Sets.newHashSet(authority));
        expected.setId(2L);
        expected.setOrders(Lists.newArrayList(new Order()));
        expected.setAccountExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIslocked(false);
        expected.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
        expected.setIsEnabled(true);
        Mockito.when(repository.findByEmail(Mockito.any())==null).thenThrow(new UsernameNotFoundException("Brak użytkownika"));
        //when
        service.loadUserByUsername("wrong@mail.com");
    }
}
