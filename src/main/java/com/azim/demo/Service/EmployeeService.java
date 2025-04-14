package com.azim.demo.Service;

import com.azim.demo.DTO.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();
    EmployeeStatusDTO getEmployeeStatusById(Long id);
    List<EmployeeDTO> getEmployeeByStatus(String employeeStatus);
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO emp);
    List<EmployeeDTO> getEmployeeByDepatment(String department);
    List<EmployeeDTO> getEmployeeByPosition(String position);
    List<EmployeeDTO> searchEmployees(EmployeeSearchCriteria criteria);
    LoginResponse loginEmployee(LoginRequest request);



}
