package com.eazybytes.springsecuritysection2.config;

import com.eazybytes.springsecuritysection2.model.Customer;
import com.eazybytes.springsecuritysection2.repository.CustomerRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazybankUserDetailsServiceImp implements UserDetailsService {

    private CustomerRepository customerRepository;

    public EazybankUserDetailsServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + username));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(customer.getRole()));
        User user = new User(customer.getEmail(), customer.getPwd(), authorities);
        System.out.println(user.getUsername());
        return user;
    }
}
