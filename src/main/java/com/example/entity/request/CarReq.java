package com.example.entity.request;


import com.example.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarReq {

    private String brand;
    private String model;
    private String year;
    private String color;
    private int stock;
    private double price;
    private Branch branch_name;
    List<OrderCarReq> orderDetailsList;


}
