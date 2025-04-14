package com.azim.demo.Service;

import com.azim.demo.DTO.LeaveDecisionDto;
import com.azim.demo.DTO.LeaveResponseDto;
import com.azim.demo.Entity.Leave;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaveService {
    LeaveResponseDto leaveRequest(Long employeeId,LeaveResponseDto leaveResponseDto);
    String leaveDecision(Long employeeId, LeaveDecisionDto leaveDecisionDto);
}
