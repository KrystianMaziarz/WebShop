package pl.com.store.webstore.controllers.dtos;

import pl.com.store.webstore.entities.Address;
import pl.com.store.webstore.services.validators.EmailValidator;
import pl.com.store.webstore.services.validators.PasswordStrengthValidator;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

public class CustomerDto implements Serializable {

    private Long id;
    @PasswordStrengthValidator
    private String password;

    private String firstname;

    private String lastname;

    private Address address;

    @EmailValidator
    private String email;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDto)) return false;
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getFirstname(), that.getFirstname()) &&
                Objects.equals(getLastname(), that.getLastname()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassword(), getFirstname(), getLastname(), getAddress(), getEmail());
    }
}
