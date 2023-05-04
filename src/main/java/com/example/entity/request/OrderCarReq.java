package com.example.entity.request;
import lombok.Data;

import java.util.List;
@Data
public class OrderCarReq {
    private Long id;
    private Long customer_id;
    private Long staff_id;
    private Double total;
    private Double discount;
}
