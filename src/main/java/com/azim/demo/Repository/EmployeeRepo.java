package com.azim.demo.Repository;


import com.azim.demo.ENUM.EmployeeStatus;
import com.azim.demo.Entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> , EmployeeCustomRepo {
//    List<Employee> findByDepartment(String department);
//    List<Employee> findByNameContaining(String keyword);


    List<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus);
    List<Employee> findByDepartment(String department);

    List<Employee> findByPosition(String position);
    Optional<Employee> findByEmail(String email);



}
