package pl.com.store.webstore.controllers.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
@Data
public class AddressDto implements Serializable {

    private Long id;
    private String city;
    private String street;
    private String number;
    private String zipcode;
    private Long customerId;

}
