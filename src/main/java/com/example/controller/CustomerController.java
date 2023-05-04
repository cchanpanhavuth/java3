package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
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
    public Customer add(@RequestBody CustomerReq customerReq){
        Customer customer = new Customer();
        customer.setFirstName(customerReq.getFirstName());
        customer.setLastName(customerReq.getLastName());
        customer.setGenderEnum(customerReq.getGender());
        customer.setPhoneNumber(customerReq.getPhoneNumber());
        customer.setEmail(customerReq.getEmail());
        this.customerService.add(customer);
        return  customer;
    }
    @PutMapping
    public Customer update(@RequestBody CustomerUpdateReq reqUpdate){
        Customer customer = this.customerService.findById(reqUpdate.getId());
        if (customer == null){
            System.out.println("Could not found Customer's ID ");
            return  null;
        }
        customer.setFirstName(reqUpdate.getFirstName());
        customer.setLastName(reqUpdate.getLastName());
        customer.setGenderEnum(reqUpdate.getGender());
        customer.setPhoneNumber(reqUpdate.getPhoneNumber());
        customer.setEmail(reqUpdate.getEmail());
        this.customerService.update(customer);
        return null;
    }
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        Customer customer = this.customerService.findById(id);
        if (customer == null){
            System.out.println("Could not find Customer's ID");
            return false;
        }
        return this.customerService.deleteById(id);
    }


}
