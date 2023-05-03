//package com.example.controller;
//
//import com.example.entity.projection.CarProjection;
//import com.example.entity.response.Pagination;
//import com.example.service.CarService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/car")
//public class CarController {
//
//    private CarService carService;
////    private CategoryService categoryService;
////    private CarModelService carModelService;
////    //private InventoryService inventoryService;
////    //private BrandService brandService;
////    //private ManufacturerLocationService manufacturerLocationService;
////    //private BranchService branchService;
////    private CarFeaturesService carFeaturesService;
////
////    @Autowired
////    public CarController(
////            CarService carService,
////            CategoryService categoryService,
////            CarModelService carModelService,
//////            InventoryService inventoryService,
//////            BrandService brandService,
//////            ManufacturerLocationService manufacturerLocationService,
//////            BranchService branchService,
////            CarFeaturesService carFeaturesService
////    ){
////        this.carService = carService;
////        this.categoryService = categoryService;
////        this.carModelService = carModelService;
//////        this.inventoryService = inventoryService;
//////        this.branchService = branchService;
//////        this.manufacturerLocationService = manufacturerLocationService;
//////        this.brandService = brandService;
////        this.carFeaturesService = carFeaturesService;
////    }
////
////    @PostMapping
////    public Car add(@RequestBody CarAddRequest req){
////        Category category = this.categoryService.findById(req.getCategoryId());
////        CarModel carModel = this.carModelService.findById(req.getCarModelId());
////        //Inventory inventory = this.inventoryService.findById(req.getInventoryId());
////        //Brand brand = this.brandService.findById(req.getBrandId());
////        //ManufacturerLocation manufacturerLocation = this.manufacturerLocationService.findById(req.getManufacturerLocationId());
////        //Branch branch = this.branchService.findById(req.getBranchId());
////        CarFeatures carFeatures = this.carFeaturesService.findById(req.getCarFeatureId());
////        if(category ==null){
////            System.out.println("Category is not found!");
////            return null;
////        } else if (carModel ==null) {
////            System.out.println("Car Model is not found!");
////            return null;
//////        }else if (inventory ==null) {
//////            System.out.println("Inventory is not found!");
//////            return null;
//////        }else if (brand ==null) {
//////            System.out.println("Brand is not found!");
//////            return null;
//////        }else if (manufacturerLocation ==null) {
//////            System.out.println("Manufacturer Location is not found!");
//////            return null;
//////        }else if (branch ==null) {
//////            System.out.println("Branch Location is not found!");
//////            return null;
////        }else if (carFeatures ==null) {
////            System.out.println("Car Features Location is not found!");
////            return null;
////        }
////        Car car = new Car();
////        //car.setBrand_name(brand);
////        //car.setInventory(inventory);
////        //car.setManufacturer_location(manufacturerLocation);
////        //car.setBranch_name(branch);
////        //car.setModel_name(carModel);
////        //car.setFeatures_id(carFeatures);
////        car.setPrice(req.getPrice());
////        //car.setCategory_name(category);
////        this.carService.add(car);
////        return null;
////        //return car;
////    }
////
////    @PutMapping
////    public Car update(@RequestBody CarUpdateRequest req){
////        Category category = this.categoryService.findById(req.getCategoryId());
////        CarModel carModel = this.carModelService.findById(req.getCarModelId());
//////        Inventory inventory = this.inventoryService.findById(req.getInventoryId());
//////        Brand brand = this.brandService.findById(req.getBrandId());
//////        ManufacturerLocation manufacturerLocation = this.manufacturerLocationService.findById(req.getManufacturerLocationId());
//////        Branch branch = this.branchService.findById(req.getBranchId());
////        CarFeatures carFeatures = this.carFeaturesService.findById(req.getCarFeatureId());
////        if(category ==null){
////            System.out.println("Category is not found!");
////            return null;
////        } else if (carModel ==null) {
////            System.out.println("Car Model is not found!");
////            return null;
//////        }else if (inventory ==null) {
//////            System.out.println("Inventory is not found!");
//////            return null;
//////        }else if (brand ==null) {
//////            System.out.println("Brand is not found!");
//////            return null;
//////        }else if (manufacturerLocation ==null) {
//////            System.out.println("Manufacturer Location is not found!");
//////            return null;
//////        }else if (branch ==null) {
//////            System.out.println("Branch Location is not found!");
//////            return null;
////        }else if (carFeatures ==null) {
////            System.out.println("Car Features Location is not found!");
////            return null;
////        }
////        Car car = new Car();
////        //car.setBrand_name(brand);
////        //car.setInventory(inventory);
////        //car.setManufacturer_location(manufacturerLocation);
////        //car.setBranch_name(branch);
////        //car.setModel_name(carModel);
////        //car.setFeatures_id(carFeatures);
////        car.setPrice(req.getPrice());
////        //car.setCategory_name(category);
////        this.carService.update(car);
////        return null;
////    }
//
//    @DeleteMapping("/{id}")
//    public boolean delete(@PathVariable Long id){
//        return this.carService.deleteById(id);
//    }
//
//    @GetMapping("/price/{price}")
//    public CarProjection findByCarPrice(@PathVariable Double price){
//        return this.carService.findByCarPrice(price);
//    }
//
//    @GetMapping("/all")
//    public Map<String, Object> findAllCar(Pagination pagination){
//        List<CarProjection> car = this.carService.findCarProjectionAll(pagination);
//        Map<String, Object> map = new HashMap<>();
//        map.put("data", car);
//        map.put("pagination", pagination);
//        return map;
//    }
//
//    @GetMapping
//    public List<CarProjection> findByPrice(){
//        return this.carService.findAllCar();
//    }
//
////    @PostMapping
////    public CarFeatures add(@RequestBody CarFeatures carFeatures){
////        return this.carFeaturesService.add(carFeatures);
////    }
//
//
//
//}
