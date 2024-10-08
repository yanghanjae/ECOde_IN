package com.project.ecodein.controller;


import com.project.ecodein.dto.*;
import com.project.ecodein.service.OrderingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderingService ORDERING_SERVICE;

	public OrderController(OrderingService orderingService) {

		this.ORDERING_SERVICE = orderingService;
	}

	// 발주 목록 페이지
//	@GetMapping({"/{page}", "/{page}/{status}"})
//	public String order(Model model,
//						@PathVariable("page") Integer page,
//						@RequestParam(required = false) String query,
//                        @PathVariable(name = "status", required = false) String status) {
//
//		Page<Ordering> orders = ORDERING_SERVICE.getOrders(page, query, status);
//		model.addAttribute("orders", orders);
//		return "order/order";
//	}


	// 발주 목록 페이지
// 발주 목록 페이지 - status가 없는 경우
	@GetMapping("/{page}")
	public String orderWithoutStatus(Model model,
									 @PathVariable("page") Integer page,
									 @RequestParam(required = false) String query) {
		Page<Ordering> orders = ORDERING_SERVICE.getOrders(page, query, "all");  // 기본값으로 "all" 전달
		model.addAttribute("orders", orders);
		return "order/order";
	}

	// 발주 목록 페이지 - status가 있는 경우
	@GetMapping("/{page}/{status}")
	public String orderWithStatus(Model model,
								  @PathVariable("page") Integer page,
								  @RequestParam(required = false) String query,
								  @PathVariable(name = "status") String status) {

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




	// 상품 검색 기능 구현1
	@GetMapping("/search")
	@ResponseBody
	public List<Stock> searchProducts(@RequestParam(name = "query", required = false) String query, Model model) {
		// 빈 검색어가 전달될 경우 기본 처리를 하거나 검색 결과 없음 처리
//		if (query == null || query.isEmpty()) {
//			model.addAttribute("message", "검색 결과가 없습니다.");
//			return "order/orderAdd";
//		}

		// 검색 로직 수행 (Stock 검색)
		List<Stock> stocks = ORDERING_SERVICE.searchStocksByName(query);
//		model.addAttribute("stocks", stocks);
		return stocks;
	}


	// 상품 검색 기능 구현2
//	@GetMapping("/search")
//	@ResponseBody  // 이 부분이 중요합니다. 이 어노테이션이 있어야 JSON 형식으로 반환됩니다.
//	public List<Stock> searchProducts(@RequestParam(name = "query", required = false) String query) {
//		if (query == null || query.isEmpty()) {
//			return Collections.emptyList();  // 빈 리스트를 반환
//		}
//
//		// 검색 로직 수행
//		List<Stock> stocks = ORDERING_SERVICE.searchStocksByName(query);  // Stock 검색
//		return stocks;  // JSON으로 변환되어 반환
//	}






	// 발주 등록 처리
    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderPoolDTO orderPool) {
        orderPool.getOrder_nos().forEach(System.out::println);
        log.info(String.valueOf(orderPool));
        return "redirect:/order/1/all";
    };


    // 등록된 상품처리1
//	@PostMapping("/add")
//	public String orderAddPost(@ModelAttribute Ordering ordering, @ModelAttribute Stock stock) {
//		ORDERING_REPOSITORY.registerOrder(ordering, stock); // 수정
//		return "redirect:/order"; // 수정
//	}

	// 발주등록된 상품처리2
//	@PostMapping("/add/search")
//	public String orderAddPost(@RequestParam("productIds") List<Integer> productIds,
//							   @RequestParam("quantities") List<Integer> quantities, Model model) {
//		// 각 상품 ID와 수량을 바탕으로 발주 등록 처리
//		ORDERING_SERVICE.registerOrder(productIds, quantities);
//		return "redirect:/order";
//	}
}