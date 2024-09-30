package com.project.ecodein.service;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.User;
import com.project.ecodein.repository.AdminRepository;
import com.project.ecodein.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final AdminRepository ADMIN_REPOSITORY;
    private final UserRepository USER_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    public UserService(AdminRepository adminRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.ADMIN_REPOSITORY = adminRepository;
        this.USER_REPOSITORY = userRepository;
        this.PASSWORD_ENCODER = passwordEncoder;
    }

    // [240927] 장유빈 기능 추가
    // 기능 상세 : 관리자 메뉴 -> 관리자 승인 요청 조회 기능
    public List<Admin> findAllByAdminRecognize () {
        return ADMIN_REPOSITORY.findByAdminRecognize();
    }

    public void adminAuthPass (String adminId) {
        ADMIN_REPOSITORY.updateAdminAuthByAdminId(adminId);
    }

    // [240930] 장유빈 기능 추가
    // 기능 상세 : 비밀번호 변경 -> 비밀번호 확인 기능
    public Optional<User> userPasswordCheck (String user_id, String user_password) {
        User user = USER_REPOSITORY.findById(user_id).get();

        String _password = user.getUser_password();

        if (PASSWORD_ENCODER.matches(user_password, _password)) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    // [240930] 장유빈 기능 추가
    // 기능 상세 : 비밀번호 변경 -> 비밀번호 변경 기능
    public Integer userPasswordModify (String user_id, String user_password) {
        String newPassword = PASSWORD_ENCODER.encode(user_password);

        return USER_REPOSITORY.updatePassword(user_id, newPassword);
    }
}
