package pl.com.store.webstore.controllers.dtos;

import java.io.Serializable;
import java.util.Objects;

public class AddressDto implements Serializable {

    private Long id;
    private String city;
    private String street;
    private String number;
    private String zipcode;
    private Long customerId;

    public Long getId() {
        return id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        if (!(o instanceof AddressDto)) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getStreet(), that.getStreet()) &&
                Objects.equals(getNumber(), that.getNumber()) &&
                Objects.equals(getZipcode(), that.getZipcode()) &&
                Objects.equals(getCustomerId(), that.getCustomerId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getNumber(), getZipcode(), getCustomerId());
    }
}
