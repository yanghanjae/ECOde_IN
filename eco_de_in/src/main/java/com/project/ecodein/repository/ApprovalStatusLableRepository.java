package com.project.ecodein.repository;

import com.project.ecodein.dto.ApprovalStatusLable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalStatusLableRepository extends JpaRepository<ApprovalStatusLable, Integer> {

    @Query(value = "select * from approval_status_lable where approval_no = :approval_no order by update_lable_date " +
        "desc limit 1", nativeQuery = true)
    ApprovalStatusLable findApprovalStatusLableById(int approval_no);
}
