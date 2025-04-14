package com.azim.demo.Repository;

import com.azim.demo.DTO.EmployeeSearchCriteria;
import com.azim.demo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeCustomRepo {
    List<Employee> searchEmployee (EmployeeSearchCriteria criteria);

}
