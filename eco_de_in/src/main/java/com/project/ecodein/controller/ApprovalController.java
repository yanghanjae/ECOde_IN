package com.project.ecodein.controller;

import com.project.ecodein.dto.ApprovalDTO;
import com.project.ecodein.service.ApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/approval")
public class ApprovalController {

    private final ApprovalService APPROVAL_SERVICE;

    public ApprovalController(ApprovalService approvalService) {
        APPROVAL_SERVICE = approvalService;
    }

    @GetMapping("/{page}")
    public String approvalList(Model modal, @PathVariable(name = "page") Integer page) {
        if (page == null) {
            page = 1;
        }

        Page<ApprovalDTO> lists = APPROVAL_SERVICE.getApprovals((int) page);

        System.out.println("lists : " + lists);

        modal.addAttribute("approvals", lists);
        return "approval/approval-list";
    }

    @GetMapping("/detail/{approval_no}")
    public String approvalDetail(@PathVariable("approval_no") String approval_no) {
        return "approval/approval-detail";
    }
}
