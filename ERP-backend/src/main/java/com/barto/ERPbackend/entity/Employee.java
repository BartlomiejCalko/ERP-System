package com.barto.ERPbackend.entity;

import com.barto.ERPbackend.dto.EmployeeDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String salary;

    @OneToOne(mappedBy = "employee")
    private Operator operator;

    public static Employee of(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setSalary(dto.getSalary());
        return employee;
    }

}
