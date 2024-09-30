package com.project.ecodein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.ecodein.common.Pagenation;
import com.project.ecodein.common.PagingButtonInfo;
import com.project.ecodein.config.SecurityConfig;
import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.Buyer;
import com.project.ecodein.dto.User;
import com.project.ecodein.repository.BuyerRepository;
import com.project.ecodein.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	private final BuyerRepository BUYER_REPOSITORY;
	private final LoginService LOGIN_SERVICE;
	private final SecurityConfig SECURITY_CONFIG;

	@Autowired
	public LoginController (BuyerRepository BUYER_REPOSITORY,
		LoginService LOGIN_SERVICE,
		SecurityConfig SECURITY_CONFIG) {

		this.BUYER_REPOSITORY = BUYER_REPOSITORY;
		this.LOGIN_SERVICE = LOGIN_SERVICE;
		this.SECURITY_CONFIG = SECURITY_CONFIG;
	}

	@GetMapping("/")
	public String login(HttpSession httpSession) {
		httpSession.invalidate ();
		return "login/login";
	}
	
	@PostMapping("/login")
	public String loginPost (@RequestParam(name = "admin", required = false) boolean admin, @RequestParam("user_id") String user_id,
		@RequestParam("user_password") String user_password, Model model) {
		if (admin) {
			log.info ("admin");
			return LOGIN_SERVICE.adminLogin (user_id, user_password, model);
		} else {
			return LOGIN_SERVICE.login (user_id, user_password, model);
		}
	}
	
	@GetMapping("/signup")
	public String signUp (Model model) {
		return "login/signUp";
	}
	
	@PostMapping("/signup")
	public String singUpPost (@ModelAttribute User user, @RequestParam("buyer_secure_code") String buyer_secure_code,
		Model model) {
		return LOGIN_SERVICE.signUp (user, buyer_secure_code, model);
	}
	
	@GetMapping("signup/modal")
	public String signUpBuyers (@RequestParam(value = "search", required = false) String search,
		@RequestParam(value = "page") int page,
		Model model) {
		
		Page<Buyer> buyers = LOGIN_SERVICE.searchBuyers (search, page, 10);
		System.out.println ("totalpages," + buyers.getTotalPages ());
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo (buyers, 5);
		
		model.addAttribute ("buyers", buyers);
		model.addAttribute ("paging", paging);
		model.addAttribute ("search", search);
		
		return "login/signUpModal :: modalContent";
	}
	
	@GetMapping("/signup/admin")
	public String AdminSignUp () {
		return "login/adminSignUp";
	}
	
	@PostMapping("/signup/admin")
	public String AdminSignUpPost (@ModelAttribute Admin admin, Model model) {
		return LOGIN_SERVICE.adminSignUp (admin, model);
	}
	
	@GetMapping("/logout")
	public String logout (HttpSession session) {
		session.invalidate ();
		return "redirect:/";
	}
}
