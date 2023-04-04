package com.example.service;
import com.example.entity.Customer;

public interface CustomerService {
    Customer add(Customer customer);
    Customer update(Customer customer);
    boolean deleteById(Long id);
    Customer findById(Long id);
}
