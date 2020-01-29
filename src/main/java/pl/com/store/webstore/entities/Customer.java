package pl.com.store.webstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstname;

    private String lastname;
    @OneToOne(orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    private boolean islocked;

    private boolean isEnabled;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate accountExpirationDate;


    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate passwordExpirationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Authority> getAuthoritySet() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean getIslocked() {
        return islocked;
    }

    public void setIslocked(boolean islocked) {
        this.islocked = islocked;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public LocalDate getAccountExpirationDate() {
        return accountExpirationDate;
    }

    public void setAccountExpirationDate(LocalDate accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    public LocalDate getPasswordExpirationDate() {
        return passwordExpirationDate;
    }

    public void setPasswordExpirationDate(LocalDate passwordExpirationDate) {
        this.passwordExpirationDate = passwordExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getIslocked() == customer.getIslocked() &&
                isEnabled == customer.isEnabled &&
                Objects.equals(getEmail(), customer.getEmail()) &&
                Objects.equals(getPassword(), customer.getPassword()) &&
                Objects.equals(getFirstname(), customer.getFirstname()) &&
                Objects.equals(getLastname(), customer.getLastname()) &&
                Objects.equals(getAddress(), customer.getAddress()) &&
                Objects.equals(getOrders(), customer.getOrders()) &&
                Objects.equals(authorities, customer.authorities) &&
                Objects.equals(getAccountExpirationDate(), customer.getAccountExpirationDate()) &&
                Objects.equals(getPasswordExpirationDate(), customer.getPasswordExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getFirstname(), getLastname(), getAddress(), getOrders(), authorities, getIslocked(), isEnabled, getAccountExpirationDate(), getPasswordExpirationDate());
    }
}
