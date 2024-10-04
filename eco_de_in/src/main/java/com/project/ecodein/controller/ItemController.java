package com.project.ecodein.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.ecodein.common.Pagenation;
import com.project.ecodein.common.PagingButtonInfo;
import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.service.ItemService;
import com.project.ecodein.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/item")
public class ItemController {
	private final ItemService ITEM_SERVICE;
	
	public ItemController (ItemService ITEM_SERVICE) {
		this.ITEM_SERVICE = ITEM_SERVICE;
	}
	
	@GetMapping("")
	public String item (Model model, @RequestParam(defaultValue = "") String search,
		@RequestParam(defaultValue = "false") boolean is_item,
		@RequestParam(defaultValue = "") Integer itemNo,
		@RequestParam(defaultValue = "1") int page) {
		
		Page<Item> items = ITEM_SERVICE.findItem (page, is_item, search, itemNo);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo (items, 5);
		
		model.addAttribute ("items", items);
		model.addAttribute ("paging", paging);
		model.addAttribute ("is_item", is_item);
		model.addAttribute ("itemNo", itemNo);
		model.addAttribute ("search", search);
		
		return "item/item";
	}
	
//	@GetMapping("/add")
//	public String addItem

}
