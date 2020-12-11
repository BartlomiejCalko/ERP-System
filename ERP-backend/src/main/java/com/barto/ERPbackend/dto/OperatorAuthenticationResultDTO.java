package com.barto.ERPbackend.dto;

import com.barto.ERPbackend.entity.Operator;
import lombok.Data;

@Data
public class OperatorAuthenticationResultDTO {

    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;

    public static OperatorAuthenticationResultDTO createUnauthenticted() {
        OperatorAuthenticationResultDTO dto = new OperatorAuthenticationResultDTO();
        dto.setAuthenticated(false);
        return dto;
    }

    public static OperatorAuthenticationResultDTO of(Operator operator) {
        OperatorAuthenticationResultDTO dto = new OperatorAuthenticationResultDTO();
        dto.setAuthenticated(true);
        dto.setFirstName(operator.getEmployee().getFirstName());
        dto.setLastName(operator.getEmployee().getLastName());
        dto.setIdOperator(operator.getIdOperator());
        return dto;
    }


}