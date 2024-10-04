package com.project.ecodein.repository;

import com.project.ecodein.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
}
