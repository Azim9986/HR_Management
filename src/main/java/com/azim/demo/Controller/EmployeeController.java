package com.azim.demo.Controller;

import com.azim.demo.DTO.*;
import com.azim.demo.ENUM.EmployeeStatus;
import com.azim.demo.Entity.Employee;
import com.azim.demo.Exceptions.EmailAlreadyExistsException;
import com.azim.demo.Repository.EmployeeRepo;
import com.azim.demo.Service.EmployeeService;
import com.azim.demo.Service.LeaveService;
import com.azim.demo.Service.LeaveServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/")
    public String greet(){
        return "Hello";
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> existing = employeeRepo.findByEmail(employeeDTO.getEmail());
        if (existing.isPresent()){
            throw new EmailAlreadyExistsException("Employee with this email already exists.");
        }
        EmployeeDTO created = employeeService.saveEmployee(employeeDTO);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/leave/{id}")
    public ResponseEntity<LeaveResponseDto> requestLeave(@PathVariable Long id,@RequestBody LeaveResponseDto leaveResponseDto){
        LeaveResponseDto created = leaveService.leaveRequest(id,leaveResponseDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("leave/{id}")
    public ResponseEntity<String> reviewLeave(@PathVariable Long id, @RequestBody LeaveDecisionDto leaveDecisionDto) {
        String response = leaveService.leaveDecision(id, leaveDecisionDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updated);
    }
    @PostMapping("/search")
    public List<EmployeeDTO> searchEmployees(@RequestBody EmployeeSearchCriteria criteria) {
        return employeeService.searchEmployees(criteria);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
