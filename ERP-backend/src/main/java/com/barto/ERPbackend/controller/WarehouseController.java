package com.barto.ERPbackend.controller;


import com.barto.ERPbackend.entity.QuantityType;
import com.barto.ERPbackend.entity.Warehouse;
import com.barto.ERPbackend.repository.QuantityTypeRepository;
import com.barto.ERPbackend.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;

    @PostMapping("/warehouses")
    Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return warehouseRepository.save(newWarehouse);
    }

    @GetMapping("/warehouses")
    List<Warehouse> listWarehouses() {
        return warehouseRepository.findAll();
    }

    @DeleteMapping("/warehouses")
    ResponseEntity deleteWarehouse(@RequestBody Long id) {
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
