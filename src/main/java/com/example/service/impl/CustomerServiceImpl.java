package com.example.service.impl;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {
    }
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer updateCustomer = customerRepository.findById(customer.getId()).orElse(null);
        if (!ObjectUtils.isEmpty(customer)){
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setGenderEnum(customer.getGenderEnum());
            updateCustomer.setPhoneNumber(customer.getPhoneNumber());
            updateCustomer.setEmail(customer.getEmail());
            return customerRepository.save(updateCustomer);

        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Customer deleteByCustomerId = customerRepository.findById(id).orElse(null);
        if (!ObjectUtils.isEmpty(deleteByCustomerId)){
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer findById(Long id) {
        return this.customerRepository.findById(id).orElse(null);
    }
}
