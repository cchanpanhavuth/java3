package com.example.repository;

import com.example.entity.Customer;
import com.example.entity.projection.CategoryProjection;
import com.example.entity.projection.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<CustomerProjection> findCustomerById(Long id);
    Page<CustomerProjection> findAllCustomerProjectionBy(Pageable pageable);

}
