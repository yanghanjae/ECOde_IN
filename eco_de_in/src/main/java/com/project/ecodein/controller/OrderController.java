package com.project.ecodein.controller;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.ecodein.dto.OrderDetail;
import com.project.ecodein.dto.OrderPoolDTO;
import com.project.ecodein.dto.Ordering;
import com.project.ecodein.entity.Stock;
import com.project.ecodein.repository.OrderDetailRepository;
import com.project.ecodein.service.OrderingService;


@Controller
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderingService ORDERING_SERVICE;
    private final OrderDetailRepository ORDER_DETAIL_REPOSITORY;

    public OrderController (OrderingService orderingService, OrderDetailRepository orderDetailRepository) {
        this.ORDERING_SERVICE = orderingService;
        this.ORDER_DETAIL_REPOSITORY = orderDetailRepository;
    }

    // 발주 목록 페이지
    @GetMapping({"/{page}", "/{page}/{status}"})
    public String order(Model model,
                        @PathVariable("page") Integer page,
                        @RequestParam(required = false) String query,
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

    // 발주 등록 페이지_상품 검색 기능
    @GetMapping("/search")
    @ResponseBody
    public List<Stock> searchProducts(@RequestParam(name = "query", required = false) String query, Model model) {

        // 검색 로직 수행 (Stock 검색)
        List<Stock> stocks = ORDERING_SERVICE.searchStocksByName(query);
        //model.addAttribute("stocks", stocks);
        return stocks;
    }

    // 발주 등록 페이지_발주 등록 처리
    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderPoolDTO orderPool) {
        orderPool.getOrder_nos().forEach(System.out::println);
        log.info(String.valueOf(orderPool));
        ORDERING_SERVICE.addOrder(orderPool);
        return "redirect:/order/1/all";
    }

    // 발주 상세 페이지
//    @GetMapping("/detail/{order_no}")
//    public String detail(@PathVariable int order_no){
//        ORDERING_SERVICE.findById(order_no);
//        return "order/orderDetail";
//    }

    // 발주 상세 페이지(버전 1)
    @GetMapping("/detail/{order_no}")
    public String detail(@PathVariable int order_no, Model model) {

        Ordering order = ORDERING_SERVICE.findById(order_no);
        log.info("Order Data: " + order);

        List<OrderDetail> orderDetails = ORDERING_SERVICE.findOrderDetails(order_no);
        log.info("Order Detail: " + orderDetails);

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);

        return "order/orderDetail";
    }
}