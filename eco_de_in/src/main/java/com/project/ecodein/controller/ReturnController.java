package com.project.ecodein.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.ecodein.dto.OrderDetail;
import com.project.ecodein.dto.Ordering;
import com.project.ecodein.entity.User;
import com.project.ecodein.repository.OrderDetailRepository;
import com.project.ecodein.service.OrderingService;
import com.project.ecodein.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/return")
public class ReturnController {

    private final HttpSession httpSession;
    private final OrderingService ORDERING_SERVICE;
    private final UserService USER_SERVICE;
    private final OrderDetailRepository ORDER_DETAIL_SERVICE;

    public ReturnController (HttpSession httpSession, OrderingService orderingService, UserService userService,
                             OrderDetailRepository orderDetailRepository) {
        this.httpSession = httpSession;
        this.ORDERING_SERVICE = orderingService;
        this.USER_SERVICE = userService;
        this.ORDER_DETAIL_SERVICE = orderDetailRepository;
    }

    @GetMapping("")
    public String returnPage () {
        return "return/return";
    }

    @GetMapping("/modal/{type}")
    public String modalPage (@PathVariable String type, Model model) {
        User user = (User) httpSession.getAttribute("user");
        LocalDateTime now = LocalDateTime.now();

        if (type.equals("regist")) {
            model.addAttribute("tabName", "반품 접수 - ");

            if (user != null) {
                model.addAttribute("buyerName", user.getBuyerCode().getBuyerName());
            }
        } else if (type.equals("detail")) {
            model.addAttribute("tabName", "반품 상세 정보 - ");
            model.addAttribute("serviceName", user.getBuyerCode().getBuyerName() + "-" + now.getMonthValue() +
                "/" + now.getDayOfMonth());
        }

        return "functional/return-modal";
    }

    @GetMapping("/fetch-order/{buyer_code}")
    @ResponseBody
    public List<Ordering> fetchOrder (Model model, @PathVariable int buyer_code) {
        return ORDERING_SERVICE.getOrderings(buyer_code);
    }

    @GetMapping("/fetch-user/{buyer_code}")
    @ResponseBody
    public List<User> fetchUser (Model model, @PathVariable int buyer_code) {
        return USER_SERVICE.findAllByBuyerCode(buyer_code);
    }

    @GetMapping("/fetch-orderDetail/{order_no}")
    @ResponseBody
    public List<OrderDetail> fetchOrderDetail (Model model, @PathVariable int order_no) {
        return ORDER_DETAIL_SERVICE.findAllByOrderId(order_no);
    }

    @PostMapping("/fetch-add")
    public String fetchAdd(@RequestBody List<OrderDetail> orderDetail) {
        System.out.println(orderDetail);
        return "redirect:modal/regist";
    }

}
