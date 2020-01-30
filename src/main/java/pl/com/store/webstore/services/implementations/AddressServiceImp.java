package pl.com.store.webstore.services.implementations;

import com.google.common.collect.Sets;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.controllers.dtos.CustomerDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.entities.Authority;
import pl.com.store.webstore.entities.Customer;
import pl.com.store.webstore.repositories.AddressRepository;
import pl.com.store.webstore.repositories.CustomerRepository;
import pl.com.store.webstore.services.AddressService;
import pl.com.store.webstore.services.implementations.mappers.CustomerMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImp implements AddressService {

    private AddressRepository repository;
    private CustomerRepository customerRepository;

    public AddressServiceImp(AddressRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Long addAddress(AddressDto addressDto, CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Authority authority = new Authority();
        authority.setAuthority("ROLE_CUSTOMER");

        customer.setAuthorities(Sets.newHashSet(authority));
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setZipcode(addressDto.getZipcode());
        Customer persistedCustomer = customerRepository.save(customer);
        address.setCustomer(persistedCustomer);
        customer.setAddress(address);
        authority.setCustomer(customer);
        Address persisted = repository.save(address);
        return persisted.getId();
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public Address findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Address does not exist !");
        }
        Address address = repository.getOne(id);
        if (address == null) {
            throw new Exception("Cannot find address with specified id");
        }
        return address;
    }

    @Override
    @Transactional
    public Address updateAddress(Long id, AddressDto addressDto) throws Exception {
        if (addressDto.getCity() == null || addressDto.getStreet() == null || addressDto.getNumber() == null || addressDto.getZipcode() == null) {
            throw new Exception("Address details are not sufficient");
        }
        if (id == null) {
            throw new Exception("User does not exist !");
        }
        Address address = repository.getOne(id);
        if (address == null) {
            throw new Exception("Cannot find address with specified id");
        }
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setZipcode(addressDto.getZipcode());
        return address;
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("Address does not exist !");
        }
        Address address = repository.getOne(id);
        if (address == null) {
            throw new Exception("Cannot find address with specified id");
        }
        repository.delete(address);
    }
}
