package com.example.controller;

import com.example.entity.Customer;
import com.example.entity.OrderCars;
import com.example.entity.SaleStaff;
import com.example.entity.request.OrderCarReq;
import com.example.entity.request.OrderCarsUpdate;
import com.example.service.CustomerService;
import com.example.service.OrderCarsService;
import com.example.service.SaleStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/orderCars")
public class OrderCarsController {
    private CustomerService customerService;
    private SaleStaffService saleStaffService;
    private OrderCarsService orderCarsService;
    @Autowired
    public OrderCarsController(CustomerService customerService, SaleStaffService saleStaffService, OrderCarsService orderCarsService) {
        this.customerService = customerService;
        this.saleStaffService = saleStaffService;
        this.orderCarsService = orderCarsService;
    }
    @PostMapping
    public OrderCars add(@RequestBody OrderCarReq req){
        Customer customer = this.customerService.findById(req.getCustomer_id());
        SaleStaff saleStaff =  this.saleStaffService.findById(req.getStaff_id());
        if(customer == null || saleStaff == null){
            System.out.println("Customer is not found!");
            return null;
        }
        OrderCars orderCars = new OrderCars();
        orderCars.setCustomer(customer);
        orderCars.setSaleStaff(saleStaff);
        orderCars.setTotalAmount(req.getTotal());
        orderCars.setDiscount(req.getDiscount());
        this.orderCarsService.add(orderCars);
        return orderCars;
    }

    @PutMapping
    public OrderCars update(@RequestBody OrderCarsUpdate req){
        Customer customer = this.customerService.findById(req.getCustomer_id());
        SaleStaff saleStaff =  this.saleStaffService.findById(req.getStaff_id());
        if(customer == null || saleStaff == null){
            System.out.println("Customer is not found!");
            return null;
        }
        OrderCars orderCars = new OrderCars();
        orderCars.setCustomer(customer);
        orderCars.setSaleStaff(saleStaff);
        orderCars.setTotalAmount(req.getTotal());
        orderCars.setDiscount(req.getDiscount());
        this.orderCarsService.update(orderCars);
        return orderCars;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.orderCarsService.deleteById(id);
    }


}
