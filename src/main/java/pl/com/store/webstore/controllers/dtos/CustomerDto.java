package pl.com.store.webstore.controllers.dtos;

import lombok.Data;
import pl.com.store.webstore.services.validators.EmailValidator;
import pl.com.store.webstore.services.validators.PasswordStrengthValidator;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {

    private Long id;
    @PasswordStrengthValidator
    private String password;
    private String firstname;
    private String lastname;
    private AddressDto addressDto;
    @EmailValidator
    private String email;
}
