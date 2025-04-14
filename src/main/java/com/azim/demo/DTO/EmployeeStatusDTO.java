package com.azim.demo.DTO;

import com.azim.demo.ENUM.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class EmployeeStatusDTO {

    private long id;
    private EmployeeStatus employeeStatus;

    public EmployeeStatusDTO() {
    }

    public EmployeeStatusDTO(long id, EmployeeStatus employeeStatus) {
        this.id = id;
        this.employeeStatus = employeeStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
