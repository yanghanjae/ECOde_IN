package com.project.ecodein.controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.ecodein.dto.Buyer;
import com.project.ecodein.dto.Search;
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

	@GetMapping (value = { "/{buyer_status}", "/{buyer_status}/{page}" })
	public String buyerList (
		Model model,
		@PathVariable (name = "buyer_status") String buyer_status,
		@PathVariable (name = "page", required = false) Integer page,
		@RequestParam (name = "keyword", required = false) String keyword) {

		Search search = new Search ();

		if (page == null) {

			page = 1;

		}

		model.addAttribute ("search", search);

		log.info ("search - address-{}, keyword - {}", search, keyword);

		Page<Buyer> list = buyerService.buyers ((int) page, keyword, buyer_status);

		model.addAttribute ("buyers", list);

		return "buyer/buyer";

	}

	@GetMapping ("/add")
	public String buyerAdd () {

		return "buyer/add";

	}

	@PostMapping ("/add")
	public String buyerAdd (@ModelAttribute Buyer buyer, Model model) {

		Buyer _buyer = buyerService.buyerInsert (buyer);

		if (_buyer != null) {

			model.addAttribute ("message", "거래처 정보가 정상 등록 되었습니다.");

		}

		log.info ("save buyer - {}", _buyer);
		return "redirect:/buyer/default/1?keyword=";

	}

	@GetMapping ("/detail/{buyer_code}")
	@ResponseBody
	public Optional<Buyer> modifty (@PathVariable ("buyer_code") Long buyer_code) {

		Optional<Buyer> buyer = buyerService.buyerDetail (buyer_code);

		log.info ("buyer - {}", buyer);

		return buyer;

	}
	
	@GetMapping ("/status-update/{buyer_code}")
	@ResponseBody
	public int statusUpdate (@PathVariable("buyer_code") Long buyer_code) {
		log.info ("test");
		buyerService.updateStatus (buyer_code);
		return 1;
	}
	
	@PostMapping ("/modify")
	@ResponseBody
	public String modify(@ModelAttribute Buyer buyer) {
		System.out.println ("buyer init : " + buyer);
		
		 buyerService.updateBuyer (buyer);
		
		
		return null;
	}

}
