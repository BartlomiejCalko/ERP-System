package com.barto.ERPbackend.controller;

import com.barto.ERPbackend.entity.Employee;
import com.barto.ERPbackend.entity.Item;
import com.barto.ERPbackend.repository.EmployeeRepository;
import com.barto.ERPbackend.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping("/items")
    Item newItem(@RequestBody Item newItem) {
        return itemRepository.save(newItem);
    }

    @GetMapping("/items")
    List<Item> listItems() {
        return itemRepository.findAll();
    }

    @DeleteMapping("/items")
    ResponseEntity deleteItem(@RequestBody Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
