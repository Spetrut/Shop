package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.customers.CustomerDtoIn;
import ro.msg.learning.shop.dtos.customers.CustomerDtoOut;
import ro.msg.learning.shop.mappers.CustomerMapper;
import ro.msg.learning.shop.services.CustomerService;

import java.security.Principal;

@Slf4j
@RequestMapping("/customer")
@RestController
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;


    @PostMapping(path = "/user")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDtoOut create(@RequestBody CustomerDtoIn customerDtoIn) {

        return CustomerMapper.toOutBound(customerService.createCustomer(customerDtoIn));

    }

    @GetMapping(path = "/profile")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoOut profile(Principal principal) {
        return CustomerMapper.toOutBound(customerService.getProfile(principal.getName()));
//        return CustomerMapper.toOutBound(customerService.getProfile(SecurityContextHolder.getContext().getAuthentication().getName()));

    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody CustomerDtoIn customerDtoIn) {

        customerService.delete(customerDtoIn);


    }


}