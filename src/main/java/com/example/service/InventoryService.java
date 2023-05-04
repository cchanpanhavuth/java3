package com.example.service;
import com.example.entity.Inventory;
import com.example.entity.projection.CustomerProjection;
import com.example.entity.projection.InventoryProjection;
import com.example.entity.request.InventoryReq;
import com.example.entity.request.InventoryUpdateReq;
import com.example.entity.response.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Inventory add(InventoryReq req);
    Inventory update(InventoryUpdateReq req);
    boolean deleteById(Long id);
    Inventory findById(Long id);
    InventoryProjection findInventorById(Long id);
    List<InventoryProjection> findAllInventoryProjectionBy(Pagination pagination);
}
