package com.azim.demo.Controller;

import com.azim.demo.DTO.*;
import com.azim.demo.ENUM.EmployeeStatus;
import com.azim.demo.Entity.Employee;
import com.azim.demo.Service.EmployeeService;
import com.azim.demo.Service.LeaveService;
import com.azim.demo.Service.LeaveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveService leaveService;

//    @GetMapping
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
//        List<EmployeeDTO> employees = employeeService.getAllEmployees();
//        return ResponseEntity.ok(employees);
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
//        EmployeeDTO employee = employeeService.getEmployeeById(id);
//        return ResponseEntity.ok(employee);
//    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
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

//    @GetMapping("/isActive/{id}")
//    public EmployeeStatusDTO getEmployeeStatusById (@PathVariable Long id){
//        return employeeService.getEmployeeStatusById(id);
//    }
//
//    @GetMapping("/status/{status}")
//    public List<EmployeeDTO> searchByStatus(@PathVariable String status) {
//        EmployeeStatus empStatus = EmployeeStatus.valueOf(status.toUpperCase());
//        return employeeService.getEmployeeByStatus(String.valueOf(empStatus));
//    }
//
//    @GetMapping("/department/{dept}")
//    public List<EmployeeDTO> searchByDepartment(@PathVariable String dept){
//        return employeeService.getEmployeeByDepatment(dept);
//    }
//    @GetMapping("/position/{position}")
//    public List<EmployeeDTO> searchByPosition(@PathVariable String position){
//        return employeeService.getEmployeeByPosition(position);
//    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = employeeService.loginEmployee(request);
        return ResponseEntity.ok(response);
    }



}
