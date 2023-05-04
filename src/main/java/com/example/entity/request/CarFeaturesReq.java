package com.example.entity.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarFeaturesReq {

    private String featureDescription;

    private List<CustomerReq> customers;
}
