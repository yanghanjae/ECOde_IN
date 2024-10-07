package com.project.ecodein.controller;

import com.project.ecodein.service.OrderingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContoller {

    private final OrderingService ORDERING_SERVICE;

    public MainContoller(OrderingService orderingService) {
        this.ORDERING_SERVICE = orderingService;
    }

	@RequestMapping ("/main")
	public String index (Model model) {
        model.addAttribute("orders", ORDERING_SERVICE.getOrderings());
		return "main";

	}

    @GetMapping("/admin-management")
    public String adminManagement (Model model) {
        model.addAttribute("menuName", "관리자 승인 요청관리");
        model.addAttribute("auth", "admin-management");
        return "functional/modal";
    }

}
