package com.project.ecodein.service;

import com.project.ecodein.dto.ApprovalDTO;
import com.project.ecodein.repository.ApprovalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ApprovalService {

    private final ApprovalRepository APPROVAL_REPOSITORY;
    private final ModelMapper modelMapper;

    public ApprovalService(ApprovalRepository APPROVAL_REPOSITORY, ModelMapper modelMapper) {
        this.APPROVAL_REPOSITORY = APPROVAL_REPOSITORY;
        this.modelMapper = modelMapper;
    }

    // 목록 출력
    public Page<ApprovalDTO> getApprovals(int page) {

        Sort sort = Sort.by(Sort.Direction.DESC, "approvalRegistDate");
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        return APPROVAL_REPOSITORY.findAll(pageable).map(approval -> modelMapper.map(approval, ApprovalDTO.class));

    }
}
