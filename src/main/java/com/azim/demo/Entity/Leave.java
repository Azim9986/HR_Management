package com.azim.demo.Entity;

import com.azim.demo.ENUM.LeaveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "leave_table")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String remark;

    public Leave() {
    }

    public Leave(Long leaveId, LocalDate startDate, LocalDate endDate, String reason, String remark, Employee employee, LeaveStatus leaveStatus) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.remark = remark;
        this.employee = employee;
        this.leaveStatus = leaveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private LeaveStatus leaveStatus = LeaveStatus.PENDING;

    public Leave(Long leaveId, LocalDate startDate, LocalDate endDate, String reason) {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
