package com.project.ecodein.controller;


import com.project.ecodein.dto.Ordering;
import com.project.ecodein.service.OrderingService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/order")
public class OrderController {


	private final OrderingService ORDER_SERVICE;


	public OrderController(OrderingService orderService) {
		this.ORDER_SERVICE = orderService;
	}


	// 발주 목록 페이지
	@GetMapping("/{page}")
	public String order(Model model, @PathVariable("page") Integer page, @RequestParam(required = false) String query) {

		Page<Ordering> orders = ORDER_SERVICE.getOrders(page, query);
		model.addAttribute("orders", orders);
		return "order/order";

	}


	// 검색 기능 구현
//	@GetMapping("/search")
//	public String searchOrders(@RequestParam("query") String query, Model model) {
//		// 서비스에서 검색 결과를 가져옴
//		List<Ordering> searchResults = (List<Ordering>) ORDER_SERVICE.searchOrders(query);
//		model.addAttribute("orders", searchResults);
//		return "order/order";
//	}


	// 삭제 기능 구현
    @GetMapping("/delete/{order_no}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int order_no) {
        ORDER_SERVICE.deleteOrder(order_no);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



//    // 발주 목록 페이지
//    @GetMapping("")
//    public String order() {
//
//        return "order/order";
//    }


	// 페이지네이션
	@GetMapping("/list")
	public String orderList(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "query", required = false) String query,
			Model model) {
		// 페이지네이션 및 검색 결과 조회
		Page<Ordering> orders = ORDER_SERVICE.getOrders(page, query);
		model.addAttribute("orders", orders.getContent());
		model.addAttribute("totalPages", orders.getTotalPages());
		model.addAttribute("currentPage", page);
		model.addAttribute("query", query);
		return "order/order";
	}

	@GetMapping("/add")
	public String orderAdd() {

		return "order/orderAdd";
	}

	@PostMapping("/add")
	public String orderAddPost() {

		return "order/orderAdd";
	}

}








