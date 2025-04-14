package com.azim.demo.DTO;

public class LoginResponse {
    private String message;
    private Long employeeId;
    private String employeeName;

    public LoginResponse(String message, Long employeeId, String employeeName) {
        this.message = message;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
