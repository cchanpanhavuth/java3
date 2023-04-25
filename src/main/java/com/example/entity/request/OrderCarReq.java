package com.example.entity.request;
import lombok.Data;

import java.util.List;
@Data
public class OrderCarReq {
    private List<OrderCarDetailReq> orderCarDetailReqList;
    private Long customer_id;
    private Long staff_id;
}
