package com.example.service;
import com.example.entity.Inventory;

public interface InventoryService {
    Inventory add(Inventory inventory);
    Inventory update(Inventory inventory);
    boolean deleteById(Long id);
    Inventory findById(Long id);
}
