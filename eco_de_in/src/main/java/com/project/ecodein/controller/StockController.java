package com.project.ecodein.controller;

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

import com.project.ecodein.common.Pagenation;
import com.project.ecodein.common.PagingButtonInfo;
import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/stock")
public class StockController {
	
	private final StockService STOCK_SERVICE;

	public StockController (StockService STOCK_SERVICE) {
		this.STOCK_SERVICE = STOCK_SERVICE;
	}

	@GetMapping("")
	public String stock (Model model, @RequestParam(defaultValue = "") String search,
		@RequestParam(defaultValue = "false") boolean is_item,
		@RequestParam(defaultValue = "") Integer storage_no,
		@RequestParam(defaultValue = "0") int page) {
		
		Page<Stock> stocks = STOCK_SERVICE.findStock (page, is_item, search, storage_no);
		model.addAttribute ("stocks", stocks);
		model.addAttribute ("storages", STOCK_SERVICE.findAllStorage ());
		model.addAttribute ("currentPage", stocks.getNumber ());
		model.addAttribute ("totalPages", stocks.getTotalPages ());
		model.addAttribute ("hasNext", stocks.hasNext ());
		model.addAttribute ("hasPrevious", stocks.hasPrevious ());
		model.addAttribute ("is_item", is_item);
		model.addAttribute ("selectedStorage", storage_no);
		model.addAttribute ("search", search);
		
		return "stock/stock";
	}
	
	@ResponseBody
	@GetMapping("/{stock_no}")
	public Stock stockDetail (@PathVariable int stock_no) {
		return STOCK_SERVICE.findByStockNo(stock_no).orElse (new Stock());
	}
	
	@PostMapping("/edit")
	public String stockEdit (@RequestParam int editStockNo, @RequestParam int editQuantity) {
		
		STOCK_SERVICE.editStock(editStockNo, editQuantity);
		
		return "redirect:/stock";
	}
	
	@GetMapping("/add")
	public String addStock (Model model) {
				
		return "stock/addStock";
	}
	
	@GetMapping("/add/item")
	public String itemModal (@RequestParam(value = "search", required = false) String search,
		@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		
		
		Page<Item> items = STOCK_SERVICE.searchStocks(search, page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(items);
		
		model.addAttribute ("items", items);
		model.addAttribute("paging", paging);
		model.addAttribute("itemModalSearch", search);
		
		return "stock/addStockModal :: itemModalContent";
		
	}
	
	@GetMapping("/add/storage")
	public String storageModal (@RequestParam(value = "search", required = false) String search,
		@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		
		
		Page<Storage> storages = STOCK_SERVICE.searchStorages(search, page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(storages);
		
		model.addAttribute ("storages", storages);
		model.addAttribute("paging", paging);
		model.addAttribute("storageModalSearch", search);
		
		return "stock/addStockModal :: storageModalContent";
		
	}
	
	@PostMapping("/add")
	public String addStockPost (@RequestParam int storage_no, @RequestParam int item_no, @RequestParam int quantity) {
		
		STOCK_SERVICE.addStock (item_no, storage_no, quantity);
		
		return "redirect:/stock";
	}
}
