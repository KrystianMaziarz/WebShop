package pl.com.store.webstore.services;

import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.entities.Address;

import java.util.List;

public interface AddressService {

    Long addAddress(AddressDto addressDto);

    List<Address> findAll();

    Address findById(Long id) throws Exception;

    Address updateAddress(Long id, AddressDto addressDto) throws Exception;

    void deleteById(Long id) throws Exception;
}
