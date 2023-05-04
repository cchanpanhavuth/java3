package com.example.service.impl;

import com.example.entity.Customer;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
import com.example.entity.response.Pagination;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

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
    public Customer add(CustomerReq req) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(req, customer);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(CustomerUpdateReq req) {
        Customer customer = customerRepository.findById(req.getId())
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        BeanUtils.copyProperties(req, customer);
        return customer;
    }

    @Override
    public boolean deleteById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        customerRepository.delete(customer);
        return true;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public CustomerProjection findCustomerById(Long id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));

    }


    @Override
    public List<CustomerProjection> findAllCustomerProjectionBy(Pagination pagination) {
        Page<CustomerProjection> customerProjection = customerRepository.findAllCustomerProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize()));

        pagination.setTotalCounts(customerProjection.getTotalElements());
        return customerProjection.getContent();
    }

}
