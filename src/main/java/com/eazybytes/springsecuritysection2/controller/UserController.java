package com.eazybytes.springsecuritysection2.controller;


import com.eazybytes.springsecuritysection2.constant.Contant;
import com.eazybytes.springsecuritysection2.dto.ResponseDto;
import com.eazybytes.springsecuritysection2.model.Customer;
import com.eazybytes.springsecuritysection2.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    public UserController(PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerCustomer(@RequestBody Customer customer) {
        try {

            String hashPass = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPass);
            Customer savedCustomer = customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(Contant.STATUS_201, Contant.MESSAGE_201));


        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(Contant.MESSAGE_500, Contant.MESSAGE_201));
        }
    }
}
