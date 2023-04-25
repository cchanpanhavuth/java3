package com.example.controller;

import com.example.entity.Inventory;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/inventory")
public class InventoryController{
    private InventoryService inventoryService;
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    @PostMapping
    public Inventory add(@RequestBody Inventory inventory){
        return this.inventoryService.add(inventory);
    }

    @PutMapping
    public Inventory update(@RequestBody Inventory inventory){
        return this.inventoryService.update(inventory);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return this.inventoryService.deleteById(id);
    }
}
