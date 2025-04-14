package com.azim.demo.DTO;

import java.time.LocalDate;

public class LeaveResponseDto {

    private Long leaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;

    public LeaveResponseDto(Long leaveId, LocalDate startDate, LocalDate endDate, String reason) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }

    public LeaveResponseDto() {
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
