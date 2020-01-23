package pl.com.store.webstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.com.store.webstore.services.implementations.CustomerDetailServiceImp;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerDetailServiceImp customerDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/wellcome", "/")
                .anonymous()
                .antMatchers("/wellcome/logged/**").access("hasRole('ROLE_CUSTOMER')or hasRole('ROLE_ADMIN')")
                .antMatchers("/basket/**").access("hasRole('ROLE_CUSTOMER')or hasRole('ROLE_ADMIN')")
                .antMatchers("wellcome/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests()
                .antMatchers("/orders/**").access("hasRole('ROLE_CUSTOMER')or hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests()
                .antMatchers("/setcustomer/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/findcustomer/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/customers/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/items/**").access("hasRole('ROLE_CUSTOMER')or hasRole('ROLE_ADMIN')")
                .antMatchers("/baskethtml/**").access("hasRole('ROLE_CUSTOMER')or hasRole('ROLE_ADMIN')")
                .and()
                .authorizeRequests()
                .antMatchers("/payment/**")
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/register/**")
                .permitAll()
                .antMatchers("/register2/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/wellcome/logged", true)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/wellcome");


    }
}
