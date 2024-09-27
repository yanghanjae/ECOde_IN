package com.project.ecodein.controller;

import com.project.ecodein.dto.Admin;
import com.project.ecodein.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin-manager")
    public List<Admin> adminList() {
        return userService.findAllByAdminRecognize();
    }
}
