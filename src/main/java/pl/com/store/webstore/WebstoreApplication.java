package pl.com.store.webstore;

import com.google.common.collect.Sets;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.AddressRepository;
import pl.com.store.webstore.repositories.CustomerRepository;

import javax.swing.*;
import java.time.LocalDate;

@SpringBootApplication
public class WebstoreApplication implements CommandLineRunner{

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    public WebstoreApplication(CustomerRepository customerRepository,AddressRepository addressRepository){
    this.customerRepository=customerRepository;
    this.addressRepository=addressRepository;
    }

    public static void main(String[] args) {
        ApplicationContext contexto = new SpringApplicationBuilder(WebstoreApplication.class)
                .web(WebApplicationType.SERVLET)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

    }

    @Override
    public void run(String... args) throws Exception {

        Customer byEmail = customerRepository.findByEmail("user@mail.com");
        if (byEmail==null) {
            Customer customer = new Customer();
            Authority admin = new Authority();
            admin.setAuthority("ROLE_ADMIN");
            admin.setCustomer(customer);
            Address address = new Address();
            address.setCity("Lublin");
            address.setStreet("Poziomkowa");
            address.setNumber("4/3");
            address.setZipcode("20-950");
            addressRepository.save(address);
            customer.setFirstname("Jarosław");
            customer.setLastname("Sobczak");
            customer.setAddress(address);
            customer.setEmail("user@mail.com");
            customer.setPassword(new BCryptPasswordEncoder().encode("Pass1!"));
            customer.setAuthorities(Sets.newHashSet(admin));
            customer.setAccountExpirationDate(LocalDate.now().plusMonths(3));
            customer.setIslocked(false);
            customer.setPasswordExpirationDate(LocalDate.now().plusMonths(1));
            customer.setIsEnabled(true);
            customerRepository.save(customer);
        }
    }
}
