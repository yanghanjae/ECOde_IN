package com.project.ecodein.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.ecodein.dto.Buyer;
import com.project.ecodein.service.BuyerService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping ("/buyer")
@Slf4j
public class BuyerController {

	private final BuyerService buyerService;

	public BuyerController (BuyerService buyerService) {

		this.buyerService = buyerService;

	}

	@GetMapping (value = { "/{buyer_status}/{page}/{keyword}", "/{buyer_status}/{page}" })
	public String buyerList (Model model,
		@PathVariable (name = "buyer_status") String buyer_status,
		@PathVariable (name = "page", required = false) Integer page,
		@PathVariable (name = "keyword", required = false) String keyword) {

		if (page == null) {

			page = 1;

		}

		Page<Buyer> list = buyerService.buyers ((int) page, keyword);
		model.addAttribute ("buyers", list);

		return "buyer/buyer";

	}

}
