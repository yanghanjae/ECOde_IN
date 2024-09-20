package com.project.ecodein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainContoller {

	@RequestMapping ("/main")
	public String index () {

		return "main";

	}

}
