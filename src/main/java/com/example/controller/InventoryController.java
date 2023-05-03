package com.example.controller;
import com.example.entity.Car;
import com.example.entity.Inventory;
import com.example.entity.request.InventoryReq;
import com.example.entity.request.InventoryUpdateReq;
import com.example.service.CarService;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/inventory")
public class InventoryController{
    private InventoryService inventoryService;
    private CarService carService;

    public InventoryController(InventoryService inventoryService, CarService carService) {
        this.inventoryService = inventoryService;
        this.carService = carService;
    }

    @Autowired

    @PostMapping
    public Inventory add(@RequestBody InventoryReq req){
        Car car = this.carService.findById(req.getCarID());
        if (car == null){
            System.out.println("Car could not found!!");
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setQuantity(req.getQuantity());
        inventory.setImportPrice(req.getImportPrice());
//        inventory.setCar(car);
        this.inventoryService.add(inventory);
        return inventory;
    }

    @PutMapping
    public Inventory update(@RequestBody InventoryUpdateReq updateReq){
        Car car = this.carService.findById(updateReq.getCarID());
        if (car == null){
            System.out.println("Car could not found!!");
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setQuantity(updateReq.getQuantity());
        inventory.setImportPrice(updateReq.getImportPrice());
//        inventory.setCar(car);
        this.inventoryService.update(inventory);
        return inventory;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.inventoryService.deleteById(id);
    }
}
