package com.example.service.impl;

import com.example.entity.Inventory;
import com.example.repository.InventoryRepository;
import com.example.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public Inventory update(Inventory inventory) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Inventory findById(Long id) {
        return null;
    }
}
