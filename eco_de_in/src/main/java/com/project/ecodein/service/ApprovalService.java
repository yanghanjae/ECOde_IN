package com.project.ecodein.service;

import com.project.ecodein.dto.Approval;
import com.project.ecodein.dto.ApprovalStatusLable;
import com.project.ecodein.dto.OrderDetail;
import com.project.ecodein.repository.ApprovalRepository;
import com.project.ecodein.repository.ApprovalStatusLableRepository;
import com.project.ecodein.repository.OrderDetailRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApprovalService {

    private final ApprovalRepository APPROVAL_REPOSITORY;
    private final ApprovalStatusLableRepository APPROVAL_STATUS_LABLE;
    private final OrderDetailRepository ORDER_DETAIL_REPOSITORY;

    public ApprovalService(ApprovalRepository APPROVAL_REPOSITORY,
                           ApprovalStatusLableRepository APPROVAL_STATUS_LABLE, OrderDetailRepository ORDER_DETAIL_REPOSITORY) {
        this.APPROVAL_REPOSITORY = APPROVAL_REPOSITORY;
        this.APPROVAL_STATUS_LABLE = APPROVAL_STATUS_LABLE;
        this.ORDER_DETAIL_REPOSITORY = ORDER_DETAIL_REPOSITORY;
    }

    // 목록 출력
    public Page<Approval> getApprovals(int page) {

        Sort sort = Sort.by(Sort.Direction.DESC, "approvalNo");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        return APPROVAL_REPOSITORY.findAll(pageable);
    }

    // 상세 정보
    public Approval getApproval(int approval_no) {
        return APPROVAL_REPOSITORY.findById(approval_no).orElse(null);
    }

    // 최신상태 불러오기
    public ApprovalStatusLable getApprovalStatus(int approval_no) {
        return APPROVAL_STATUS_LABLE.findApprovalStatusLableById(approval_no);
    }
    
    // 발주 상품리스트
    public List<OrderDetail> getOrderDetails(int approval_no) {
        List<OrderDetail> details = ORDER_DETAIL_REPOSITORY.findAllByOrderId(approval_no);
        return  details;
    }

    // 발주 상품 수량
    public int getOrderDetailCount(int approval_no) {
        return ORDER_DETAIL_REPOSITORY.getOrderDetailCountByOrderNo(approval_no);
    }
}
