package com.project.ecodein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.project.ecodein.config.SecurityConfig;
import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.User;
import com.project.ecodein.repository.AdminRepository;
import com.project.ecodein.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

	private final UserRepository USER_REPOSITORY;
	private final AdminRepository ADMIN_REPOSITORY;
	private final SecurityConfig SECURITY_CONFIG;
	private final HttpSession HTTP_SESSION;
	
	@Autowired
	public LoginService (UserRepository USER_REPOSITORY,
		AdminRepository ADMIN_REPOSITORY,
		SecurityConfig SECURITY_CONFIG,
		HttpSession HTTP_SESSION) {

		this.USER_REPOSITORY = USER_REPOSITORY;
		this.ADMIN_REPOSITORY = ADMIN_REPOSITORY;
		this.SECURITY_CONFIG = SECURITY_CONFIG;
		this.HTTP_SESSION = HTTP_SESSION;
	}
	
	//보안코드 검사후 회원가입.
	public String signUp (User user, String buyer_secure_code, Model model) {
		if (USER_REPOSITORY.findByUserId (user.getUser_id ()).isPresent ()) {
			model.addAttribute ("message", "이미 가입된 아이디입니다.");
			model.addAttribute ("url", "/signup");
			return "errorMessage";
		}
		if (USER_REPOSITORY.findByUserEmail (user.getUser_email ()).isPresent ()) {
			model.addAttribute ("message", "이미 가입된 이메일입니다.");
			model.addAttribute ("url", "/signup");
			return "errorMessage";
		}
		
		
		if (user.getBuyer_code ().getBuyer_secure_code ().equals (buyer_secure_code)) {
			user.setUser_password (SECURITY_CONFIG.passwordEncoder ().encode (user.getUser_password ()));
			USER_REPOSITORY.save (user);
			return "redirect:/";
		} else {
			model.addAttribute ("message", "잘못된 보안코드입니다. 다시 확인해주세요.");
			model.addAttribute ("url", "/signup");
			return "errorMessage";
		}
	}
	
	public String adminSignUp (Admin admin, Model model) {
		if (ADMIN_REPOSITORY.findByAdminId (admin.getAdmin_id ()).isPresent ()) {
			model.addAttribute ("message", "이미 가입된 아이디입니다.");
			model.addAttribute ("url", "/signup/admin");
			return "errorMessage";
		}
		admin.setAdmin_password (SECURITY_CONFIG.passwordEncoder ().encode (admin.getAdmin_password ()));
		ADMIN_REPOSITORY.save (admin);
		return "redirect:/";
	}
	
	public String login (String user_id, String user_password, Model model) {
		
		if (USER_REPOSITORY.findByUserId (user_id).isPresent ()) {
			
			if (SECURITY_CONFIG.passwordEncoder ().matches (user_password, 
				USER_REPOSITORY.findByUserId (user_id).get ().getUser_password ())) {
				
				HTTP_SESSION.setAttribute (user_id, "user_id");
				HTTP_SESSION.setAttribute ("user", "role");
				return "redirect:/main";
			} else {
				model.addAttribute ("message", "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
				model.addAttribute ("url", "/");
				return "errorMessage";
			}
		} else {
			model.addAttribute ("message", "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
			model.addAttribute ("url", "/");
			return "errorMessage";
		}
	}
	
	public String adminLogin (String user_id, String user_password, Model model) {
		if (ADMIN_REPOSITORY.findByAdminId (user_id).isPresent ()) {
			
			if (SECURITY_CONFIG.passwordEncoder ().matches (user_password, 
				ADMIN_REPOSITORY.findByAdminId (user_id).get ().getAdmin_password ())) {
				
				if(ADMIN_REPOSITORY.findByAdminId (user_id).get ().isAdmin_recognize () == false) {
					model.addAttribute ("message", "관리자 권한이 없는 계정입니다.");
					model.addAttribute ("url", "/");
					return "errorMessage";
				} else {
					HTTP_SESSION.setAttribute (user_id, "admin_id");
					HTTP_SESSION.setAttribute ("admin", "role");
					return "redirect:/main";
				}
			} else {
				model.addAttribute ("message", "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
				model.addAttribute ("url", "/");
				return "errorMessage";
			}
		} else {
			model.addAttribute ("message", "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
			model.addAttribute ("url", "/");
			return "errorMessage";
		}
	}
	
}
