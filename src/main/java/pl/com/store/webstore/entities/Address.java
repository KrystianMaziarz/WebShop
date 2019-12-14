package pl.com.store.webstore.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Address {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        private String city;
        private String street;
        private String number;
        private String zipcode;
        private boolean isToDispatch;
        @OneToOne
        private List<Customer> customers;

    public Address(String city, String street, String number, String zipcode, boolean isToDispatch) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.isToDispatch = isToDispatch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isToDispatch() {
        return isToDispatch;
    }

    public void setToDispatch(boolean toDispatch) {
        isToDispatch = toDispatch;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
