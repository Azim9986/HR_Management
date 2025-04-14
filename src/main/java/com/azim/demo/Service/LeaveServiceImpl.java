package com.azim.demo.Service;

import com.azim.demo.DTO.EmployeeDTO;
import com.azim.demo.DTO.LeaveDecisionDto;
import com.azim.demo.DTO.LeaveResponseDto;
import com.azim.demo.ENUM.LeaveStatus;
import com.azim.demo.Entity.Employee;
import com.azim.demo.Entity.Leave;
import com.azim.demo.Repository.EmployeeRepo;
import com.azim.demo.Repository.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeaveRepo leaveRepo;
    @Override
    public LeaveResponseDto leaveRequest(Long employeeId, LeaveResponseDto leaveResponseDto) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Leave leave = new Leave(leaveResponseDto.getLeaveId(),leaveResponseDto.getStartDate(),leaveResponseDto.getEndDate(),leaveResponseDto.getReason());

        leave.setEmployee(employee);
        leave.setLeaveStatus(LeaveStatus.PENDING);
        return leaveResponseDto;
    }
    public String leaveDecision(Long id, LeaveDecisionDto leaveDecisionDto){

        Leave leave = leaveRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));

        if (leave.getEmployee() != null) {
            employeeRepo.findById(leave.getEmployee().getId()).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

            leave.setLeaveStatus(leaveDecisionDto.getLeaveStatus());
            leave.setRemark(leaveDecisionDto.getRemark());

            leaveRepo.save(leave);

            return "Success";


        } else {
        return "Fail";
    }
    }

}
