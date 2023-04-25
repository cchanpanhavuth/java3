package com.example.service.impl;

import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl() {
    }
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory add(Inventory inventory) {
        inventory.setCreatedBy("Neath");
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(Inventory inventory) {
        Inventory updateInventory = inventoryRepository.findById(inventory.getId()).orElse(null);
        if(!ObjectUtils.isEmpty(inventory)){
            updateInventory.setQuantity(inventory.getQuantity());
            updateInventory.setImportPrice(inventory.getImportPrice());
            inventoryRepository.save(updateInventory);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Inventory deleteInventory = inventoryRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(deleteInventory)){
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Inventory findById(Long id) {
        return this.inventoryRepository.findById(id).orElse(null);
    }
}
