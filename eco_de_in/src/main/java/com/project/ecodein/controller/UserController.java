package com.project.ecodein.controller;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-manager")
    @ResponseBody
    public List<Admin> adminList() {
        return userService.findAllByAdminRecognize();
    }

    @GetMapping("/admin-manager/{admin_id}")
    public String adminAuth(@PathVariable String admin_id, Model model) {

        userService.adminAuthPass (admin_id);
        model.addAttribute("auth_message", "관리자 승인이 완료되었습니다.");

        return "redirect:/main";
    }

    @GetMapping("/user-management")
    public String userManagement(Model modal) {
        modal.addAttribute("menuName", "비밀번호변경");
        modal.addAttribute("auth", "user-management");
        return "functional/modal";
    }
}
