package com.example.entity.projection;

import com.example.entity.Car;
import com.example.entity.Customer;

import java.util.List;

public interface CarFeaturesProjection {

    Long getId();
    String getFeatureDescription();
    List<Customer> getCustomers();


}
