package com.example.controller;
import com.example.entity.Car;
import com.example.entity.Customer;
import com.example.entity.Inventory;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.InventoryProjection;
import com.example.entity.request.CustomerReq;
import com.example.entity.request.CustomerUpdateReq;
import com.example.entity.request.InventoryReq;
import com.example.entity.request.InventoryUpdateReq;
import com.example.entity.response.ApiResponse;
import com.example.entity.response.ApiStatus;
import com.example.entity.response.Pagination;
import com.example.service.CarService;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController{
    private InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService, CarService carService) {
        this.inventoryService = inventoryService;
    }
    @PostMapping("")
    public ApiResponse addInventory(@Validated @RequestBody InventoryReq req){
        Inventory inventory = inventoryService.add(req);
        InventoryProjection inventoryProjection = inventoryService.findInventorById(inventory.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                inventoryProjection
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getInventoryProjectionById(@PathVariable(name = "id") Long id){
        InventoryProjection inventoryProjection = inventoryService.findInventorById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                inventoryProjection
        );
    }
    @GetMapping("/{id}")
    public ApiResponse getInventoryById(@PathVariable(name = "id") Long id){
        Inventory inventory = inventoryService.findById(id);
        return new ApiResponse<>(
                ApiStatus.SUC_CREATED.getCode(),
                ApiStatus.SUC_CREATED.getMessage(),
                inventory
        );
    }
    @GetMapping("")
    public Map<String, Object> listInventory(Pagination pagination){
        List<InventoryProjection> inventoryProjections = inventoryService.findAllInventoryProjectionBy(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", inventoryProjections);
        map.put("pagination", pagination);
        return map;
    }
    @PutMapping("")
    public ApiResponse updateInventory(@RequestBody InventoryUpdateReq req){
        Inventory inventory = inventoryService.update(req);
        InventoryProjection customerProjection = inventoryService.findInventorById(inventory.getId());
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                customerProjection
        );
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteCustomer(@PathVariable(name = "id") Long id){
        Boolean isDeleted = inventoryService.deleteById(id);
        if (!isDeleted){
            return new ApiResponse<>(
                    ApiStatus.FAI_DELETED.getCode(),
                    ApiStatus.FAI_DELETED.getMessage()
            );
        }
        return new ApiResponse<>(
                ApiStatus.SUC_DELETED.getCode(),
                ApiStatus.SUC_DELETED.getMessage()
        );
    }
}
