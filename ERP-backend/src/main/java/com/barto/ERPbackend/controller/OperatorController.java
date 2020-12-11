package com.barto.ERPbackend.controller;

import com.barto.ERPbackend.dto.OperatorAuthenticationResultDTO;
import com.barto.ERPbackend.dto.OperatorCredentialsDTO;
import com.barto.ERPbackend.entity.Operator;
import com.barto.ERPbackend.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorRepository operatorRepository;

    @PostMapping("/operators")
    public Operator newOperator(@RequestBody Operator newOperator) {
        return operatorRepository.save(newOperator);
    }

    @GetMapping("/operators")
    public List<Operator> listOperators() {
        return operatorRepository.findAll();
    }

    @DeleteMapping("/operators")
    public ResponseEntity deleteOperator(@RequestBody Long id) {
        operatorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify_operator_credentials")
    public OperatorAuthenticationResultDTO verifyOperatorCredentials(@RequestBody OperatorCredentialsDTO operatorCredentialsDTO) {
        Optional<Operator> operatorOptional = operatorRepository.findByLogin(operatorCredentialsDTO.getLogin());
        if (!operatorOptional.isPresent()) {
            return OperatorAuthenticationResultDTO.createUnauthenticted();
        }
        Operator operator = operatorOptional.get();
        if (!operator.getPassword().equals(operatorCredentialsDTO.getPassword())) {
            return OperatorAuthenticationResultDTO.createUnauthenticted();
        } else {
            return OperatorAuthenticationResultDTO.of(operator);
        }
    }

}
