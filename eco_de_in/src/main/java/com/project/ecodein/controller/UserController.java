package com.project.ecodein.controller;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.User;
import com.project.ecodein.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {

    private final UserService USER_SERVICE;

    public UserController(UserService userService) {
        this.USER_SERVICE = userService;
    }

    @GetMapping("/admin-manager")
    @ResponseBody
    public List<Admin> adminList() {
        return USER_SERVICE.findAllByAdminRecognize();
    }

    @GetMapping("/admin-manager/{admin_id}")
    public String adminAuth(@PathVariable String admin_id, Model model) {

        USER_SERVICE.adminAuthPass (admin_id);
        model.addAttribute("auth_message", "관리자 승인이 완료되었습니다.");

        return "redirect:/main";
    }

    @GetMapping("/user-management")
    public String userManagement(Model modal) {
        modal.addAttribute("menuName", "비밀번호변경");
        modal.addAttribute("auth", "user-management");
        return "functional/modal";
    }

    @PostMapping("/user_management")
    @ResponseBody
    public Optional<User> userManagement(String user_id, String user_password) {
        return USER_SERVICE.userPasswordCheck(user_id, user_password);
    }

    @PostMapping("/user_password_modify")
    @ResponseBody
    public Integer userPasswordModify(String user_id, String user_password) {
        return USER_SERVICE.userPasswordModify(user_id, user_password);
    }


}
