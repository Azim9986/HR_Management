package com.azim.demo.Controller;

import com.azim.demo.DTO.EmployeeDTO;
import com.azim.demo.Entity.Employee;
import com.azim.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all-employees")
    public ResponseEntity<List<EmployeeDTO>> getAllUsers(){
        List<EmployeeDTO> all = employeeService.getAllEmployees();
        if (all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
