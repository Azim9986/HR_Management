package com.azim.demo.Service;

import com.azim.demo.DTO.*;
import com.azim.demo.ENUM.EmployeeStatus;
import com.azim.demo.Entity.Employee;
import com.azim.demo.Entity.Leave;
import com.azim.demo.Repository.EmployeeRepo;
import com.azim.demo.Repository.LeaveRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeaveRepo leaveRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private EmployeeDTO convertTODTO(Employee employee){
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    private Employee convertToEntity(EmployeeDTO dto){
        return modelMapper.map(dto, Employee.class);
    }
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(this::convertTODTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeStatusDTO getEmployeeStatusById(Long id){
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found for this id: " + id));
        return new EmployeeStatusDTO(employee.getId(), employee.getEmployeeStatus());
    }

    @Override
    public List<EmployeeDTO> getEmployeeByStatus(String employeeStatus) {
        EmployeeStatus status = EmployeeStatus.valueOf(employeeStatus.toUpperCase());
        List<Employee> employees = employeeRepo.findByEmployeeStatus(status);
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeByDepatment(String department) {
        List<Employee> employees = employeeRepo.findByDepartment(department);
        return employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeeByPosition(String position) {
        List<Employee> employees = employeeRepo.findByPosition(position);
        return  employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> searchEmployees(EmployeeSearchCriteria criteria) {
        List<Employee> employees = employeeRepo.searchEmployee(criteria);
        return employees.stream().map(this::convertTODTO).collect(Collectors.toList());
    }

    public LoginResponse loginEmployee(LoginRequest request) {
        Employee employee = employeeRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), employee.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

            return new LoginResponse("Login Successful",employee.getId(), employee.getName());
    }


    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        return convertTODTO((employee));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPassword(passwordEncoder.encode(dto.getPassword())); // Encrypt here
        employee.setDepartment(dto.getDepartment());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setEmployeeStatus(dto.getEmployeeStatus());
        employee.setJoiningDate(dto.getJoiningDate());

        Employee saved = employeeRepo.save(employee);
        return convertTODTO(saved);
    }



    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setDepartment(dto.getDepartment());
        existing.setPosition(dto.getPosition());
        existing.setSalary(dto.getSalary());
        existing.setEmployeeStatus(dto.getEmployeeStatus());



        Employee updated = employeeRepo.save(existing);
        return convertTODTO(updated);
    }



    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepo.deleteById(id);
    }
}
