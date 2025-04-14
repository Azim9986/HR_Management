package com.azim.demo.DTO;

import com.azim.demo.ENUM.EmployeeStatus;

public class EmployeeSearchCriteria {
    private String department;
    private String position;

    private EmployeeStatus  employeeStatus;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }
}
