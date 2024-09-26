package com.project.ecodein.controller;

import com.project.ecodein.dto.Ordering;
import com.project.ecodein.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 발주 목록 페이지
    @GetMapping("")
    public String order(Model model) {
        List<Ordering> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
       return "order/order";
    }

    // 검색 기능 구현
    @GetMapping("/search")
    public String searchOrders(@RequestParam("query") String query, Model model) {
        // 서비스에서 검색 결과를 가져옴
        List<Ordering> searchResults = orderService.searchOrders(query);
        model.addAttribute("orders", searchResults);
        return "order/order";  // 검색 결과를 보여줄 order.html로 이동
    }

//    // 발주 목록 페이지
//    @GetMapping("")
//    public String order() {
//
//        return "order/order";
//    }

    @GetMapping("/add")
    public String orderAdd() {

        return "order/orderAdd";
    }

    @PostMapping("/add")
    public String orderAddPost() {

        return "order/orderAdd";
    }

    @GetMapping("/{order_no}")
    public String orderDetail() {

        return "order/orderDetail";
    }

}