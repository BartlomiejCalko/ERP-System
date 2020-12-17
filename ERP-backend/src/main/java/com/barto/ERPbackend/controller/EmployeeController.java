package com.barto.ERPbackend.controller;

import com.barto.ERPbackend.dto.EmployeeDto;
import com.barto.ERPbackend.entity.Employee;
import com.barto.ERPbackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employees")
    List<EmployeeDto> listEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::of)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/employees")
    ResponseEntity deleteEmployee(@RequestBody Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
