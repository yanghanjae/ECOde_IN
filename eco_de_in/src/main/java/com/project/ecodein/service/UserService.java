package com.project.ecodein.service;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AdminRepository adminRepository;

    public UserService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // [240927] 장유빈 기능 추가
    // 기능 상세 : 관리자 메뉴 -> 관리자 승인 요청 조회 기능
    public List<Admin> findAllByAdminRecognize () {
        return adminRepository.findByAdminRecognize();
    }

    public void adminAuthPass (String adminId) {
        adminRepository.updateAdminAuthByAdminId(adminId);
    }
}
