package pl.com.store.webstore.controllers.dtos;

import lombok.NoArgsConstructor;
import pl.com.store.webstore.entities.Address;

@NoArgsConstructor
public class CustomerDto {

    private String password;

    private String firstname;

    private String lastname;

    private Address address;

    private String email;

    public CustomerDto(String password, String firstname, String lastname, Address address, String email) {
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
