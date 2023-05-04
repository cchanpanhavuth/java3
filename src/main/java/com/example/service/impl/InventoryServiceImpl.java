package com.example.service.impl;

import com.example.entity.Customer;
import com.example.entity.Inventory;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.InventoryProjection;
import com.example.entity.request.InventoryReq;
import com.example.entity.request.InventoryUpdateReq;
import com.example.entity.response.Pagination;
import com.example.repository.InventoryRepository;
import com.example.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

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
    public Inventory add(InventoryReq req) {
        Inventory inventory = new Inventory();
        BeanUtils.copyProperties(req, inventory);
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory update(InventoryUpdateReq req) {
        Inventory inventory = inventoryRepository.findById(req.getId())
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        BeanUtils.copyProperties(req, inventory);
        return inventory;
    }

    @Override
    public boolean deleteById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
        inventoryRepository.delete(inventory);
        return true;
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public InventoryProjection findInventorById(Long id) {
        return inventoryRepository.findInventorById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer could not found!!"));
    }

    @Override
    public List<InventoryProjection> findAllInventoryProjectionBy(Pagination pagination) {
        Page<InventoryProjection> inventoryProjection = inventoryRepository.findAllInventoryProjectionBy(
                PageRequest.of(pagination.getPage()-1, pagination.getSize()));

        pagination.setTotalCounts(inventoryProjection.getTotalElements());
        return inventoryProjection.getContent();
    }

}
