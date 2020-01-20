package pl.com.store.webstore.services.implementations.mappers;

import pl.com.store.webstore.controllers.dtos.AddressDto;
import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.services.implementations.CustomerServiceImp;

public class AddressMapper {

    private static CustomerServiceImp serviceImp;

    public AddressMapper(CustomerServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    public static AddressDto mapToDto(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        addressDto.setZipcode(address.getZipcode());

        return addressDto;
    }

    public static Address mapToAddress(AddressDto addressDto) throws Exception {
        Address address=new Address();
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setNumber(addressDto.getNumber());
        address.setZipcode(addressDto.getZipcode());


        return address;
    }
}
