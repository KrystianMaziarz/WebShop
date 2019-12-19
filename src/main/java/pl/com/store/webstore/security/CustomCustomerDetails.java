package pl.com.store.webstore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.com.store.webstore.entities.Customer;

import java.time.LocalDate;
import java.util.Collection;

public class CustomCustomerDetails extends Customer implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritySet();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        if (getAccountExpirationDate().isAfter(LocalDate.now().minusDays(1))) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        if (getIslocked()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getPasswordExpirationDate().isAfter(LocalDate.now().minusDays(1));
    }

    @Override
    public boolean isEnabled() {
        if (getIsEnabled()){
            return true;
        }
        return false;
    }
}
