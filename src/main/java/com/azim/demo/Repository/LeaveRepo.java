package com.azim.demo.Repository;

import com.azim.demo.Entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepo extends JpaRepository<Leave, Long> {
}
