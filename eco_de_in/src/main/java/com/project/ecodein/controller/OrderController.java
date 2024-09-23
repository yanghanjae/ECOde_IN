package com.project.ecodein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

	@GetMapping ("")
	public String order () {

		return "order/order";

	}

	@GetMapping ("/add")
	public String orderAdd () {

		return "order/orderAdd";

	}

	@PostMapping ("/add")
	public String orderAddPost () {

		return "order/orderAdd";

	}

	@GetMapping ("/{order_no}")
	public String orderDetail () {

		return "order/orderDetail";
	}


	// 임시

}
