package com.project.ecodein.controller;


import com.project.ecodein.dto.Ordering;
import com.project.ecodein.service.OrderingService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

	private final OrderingService ORDERING_SERVICE;

	public OrderController(OrderingService orderingService) {

		this.ORDERING_SERVICE = orderingService;
	}

	// 발주 목록 페이지
	@GetMapping({"/{page}", "/{page}/{status}"})
	public String order(Model model,
	@PathVariable("page") Integer page, @RequestParam(required = false) String query,
                        @PathVariable(name = "status", required = false) String status) {

		Page<Ordering> orders = ORDERING_SERVICE.getOrders(page, query, status);
		model.addAttribute("orders", orders);
		return "order/order";
	}

	// 삭제 기능 구현
    @GetMapping("/delete/{order_no}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int order_no) {
		ORDERING_SERVICE.deleteOrder(order_no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	// 페이지네이션
//	@GetMapping("/list")
//	public String orderList(
//			@RequestParam(name = "page", defaultValue = "1") int page,
//			@RequestParam(name = "query", required = false) String query,
//			Model model) {
//		// 페이지네이션 및 검색 결과 조회
//		Page<Ordering> orders = ORDER_SERVICE.getOrders(page, query);
//		model.addAttribute("orders", orders.getContent());
//		model.addAttribute("totalPages", orders.getTotalPages());
//		model.addAttribute("currentPage", page);
//		model.addAttribute("query", query);
//		return "order/order";
//	}

	// 발주 등록 페이지
	@GetMapping("/add")
	public String orderAdd(Model model) {

		return "order/orderAdd";
	}

	// 발주 등록 처리
	@PostMapping("/add")
	public String orderAddPost() {

		return "order/orderAdd";
	}

	// 등록된 상품처리1
//	@PostMapping("/add")
//	public String orderAddPost(@ModelAttribute Ordering ordering, @ModelAttribute StockDTO stock) {
//		ORDER_SERVICE.registerOrder(ordering, stock); // 수정
//		return "redirect:/order"; // 수정
//	}

	// 등록된 상품처리2
//	@PostMapping("/add")
//	public String orderAddPost(@RequestParam("productIds") List<Integer> productIds,
//							   @RequestParam("quantities") List<Integer> quantities, Model model) {
//		// 상품 정보를 처리
//		// 상품 ID와 수량을 받아서 주문 처리 로직 작성
//		ORDERING_SERVICE.registerOrder(productIds, quantities);
//		return "redirect:/order";
//	}

}