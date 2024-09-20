package com.project.ecodein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderingController {

	@GetMapping ("/order")
	public String ordering () {

		return "order/ordering";

	}

	@GetMapping ("/order/add")
	public String orderingAdd () {

		return "order/orderRegistration";

	}

	@PostMapping ("/order/add")
	public String orderingAddPost () {

		return "order/orderRegistration";

	}

	// 임시

}
