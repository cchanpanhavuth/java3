package com.example.entity.request;
import lombok.Data;

@Data
public class CustomerReq {
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
}
