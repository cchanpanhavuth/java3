package com.example.entity.request;
import com.example.entity.CarFeatures;
import com.example.entity.enums.GenderEnum;
import lombok.Data;

import java.util.List;

@Data
public class CustomerReq {
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String phoneNumber;
    private String email;
    private List<CarFeatures> carFeatures;

}
