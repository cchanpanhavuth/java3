package com.example.controller;

import com.example.entity.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public Customer add(@RequestBody Customer customer){
        return this.customerService.add(customer);
    }
    @PutMapping
    public Customer update(@RequestBody Customer customer){
        return this.customerService.update(customer);
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.customerService.deleteById(id);
    }

}
