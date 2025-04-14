package com.azim.demo.DTO;

import com.azim.demo.ENUM.LeaveStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LeaveDecisionDto {
    @Enumerated(EnumType.STRING)
    @Column(name = "leave_status")
    private LeaveStatus leaveStatus;

    @Column(name = "remark")
    private String remark;


    public LeaveDecisionDto() {
    }

    public LeaveDecisionDto(LeaveStatus leaveStatus, String remark) {
        this.leaveStatus = leaveStatus;
        this.remark = remark;
    }

    public LeaveStatus getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatus leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
