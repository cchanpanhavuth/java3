package com.example.entity.projection;

import com.example.entity.*;

public interface CarProjection {


    Long getId();
    Double getPrice();
    String getYear();
    String getColor();
    CategoryProjection getCategoryId();
    CarFeaturesProjection getFeaturesId();
    CarModelProjection getModelId();
    //InventoryProjection getInventoryId();
    //BrandProjection getBrandId();
    //BranchProjection getBranchId();
    //ManufacturerLocationProjection getManufactureId();

}
