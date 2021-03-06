package pl.com.store.webstore.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String number;
    private String zipcode;
    @OneToOne
    private Customer customer;

    public Address(String city, String street, String number, String zipcode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customers) {
        this.customer = customers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getNumber(), address.getNumber()) &&
                Objects.equals(getZipcode(), address.getZipcode()) &&
                Objects.equals(getCustomer(), address.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getNumber(), getZipcode(), getCustomer());
    }
}
