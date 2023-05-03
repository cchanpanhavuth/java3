package com.example.entity.request;

import com.example.entity.enums.GenderEnum;
import lombok.Data;

@Data
public class CustomerUpdateReq {
    private Long id;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String phoneNumber;
    private String email;
}
