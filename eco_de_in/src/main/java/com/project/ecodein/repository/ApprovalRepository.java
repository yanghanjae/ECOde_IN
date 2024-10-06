package com.project.ecodein.repository;

import com.project.ecodein.dto.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

    @Query(value = "select ap.approval_no, ap.buyer_code, approval_regist_date, status.status " +
        "from approval ap left join (select * from approval_status_lable order by update_lable_date) status " +
        "on ap.approval_no = status.approval_no " +
        "group by ap.approval_no", nativeQuery = true)
    Page<Approval> approvalsByApprovalStatus(Pageable pageable);
}
