package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.entities.Address;

public class AddressMapper {

    public static AddressDto mapToDto(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        addressDto.setZipcode(address.getZipcode());
        addressDto.setCustomerId(address.getCustomer().getId());

        return addressDto;
    }
}
