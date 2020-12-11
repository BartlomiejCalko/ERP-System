package com.barto.ERPbackend.repository;

import com.barto.ERPbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <Employee, Long>{
}
