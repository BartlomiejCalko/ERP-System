package com.barto.ERPbackend.controller;

import com.barto.ERPbackend.entity.Operator;
import com.barto.ERPbackend.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorRepository operatorRepository;

    @PostMapping("/operators")
    Operator newOperator(@RequestBody Operator newOperator) {
        return operatorRepository.save(newOperator);
    }

    @GetMapping("/operators")
    List<Operator> listOperators() {
        return operatorRepository.findAll();
    }

    @DeleteMapping("/operators")
    ResponseEntity deleteOperator(@RequestBody Long id) {
        operatorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
