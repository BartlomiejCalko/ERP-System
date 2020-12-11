package com.barto.ERPbackend.controller;

import com.barto.ERPbackend.entity.QuantityType;
import com.barto.ERPbackend.repository.QuantityTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuantityTypeController {

    private final QuantityTypeRepository quantityTypeRepository;

    @PostMapping("/quantity_types")
    QuantityType newQuantityType(@RequestBody QuantityType newQuantityType) {
        return quantityTypeRepository.save(newQuantityType);
    }

    @GetMapping("/quantity_types")
    List<QuantityType> listQuantityTypes() {
        return quantityTypeRepository.findAll();
    }

    @DeleteMapping("/quantity_types")
    ResponseEntity deleteQuantityType(@RequestBody Long id) {
        quantityTypeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
