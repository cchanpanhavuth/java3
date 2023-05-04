package com.example.service;
import com.example.entity.Customer;
import com.example.entity.enums.StatusEnum;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
import com.example.entity.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer add(CustomerReq req);
    Customer update(CustomerUpdateReq req);
    boolean deleteById(Long id);
    Customer findById(Long id);
    CustomerProjection findCustomerById(Long id);
    List<CustomerProjection> findAllCustomerProjectionBy(Pagination pagination);
}
