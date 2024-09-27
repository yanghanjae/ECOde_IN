package com.project.ecodein.service;

import java.util.List;
import java.util.Optional;

import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ecodein.dto.Ordering;
import com.project.ecodein.repository.OrderRepository;

@Service
public class OrderService {


	private final OrderRepository ORDER_SERVICE;

	public OrderService(OrderRepository ORDER_SERVICE) {
		this.ORDER_SERVICE = ORDER_SERVICE;
	}

//	public Page<Order> storages (int page, String keyword, String order_status) {
//		Sort sort = Sort.by (Sort.Order.desc ("order_no"));
//		Pageable pageable = PageRequest.of (page - 1, 10, sort);
//
//		if (keyword == null && order_status == null) {
//			return ORDER_SERVICE.findAll(pageable);
//		} else if (order_status != null && !order_status.equals ("200")) {
//			if (order_status.equals ("정상")) {
//				return ORDER_SERVICE.findAllByOrderStatus (order_status, pageable);
//			} else {
//				return ORDER_SERVICE.findAllByOrderStatusNegative (pageable);
//			}
//		}
//
//		return ORDER_SERVICE.findAllByOrderNameOrOrderSite(keyword, pageable);
//
//	}

	// 전체 발주 목록 조회
	public List<Ordering> getAllOrders() {
		return ORDER_SERVICE.findAll();
	}

	// 특정 발주 번호로 발주 검색
	public Optional<Ordering> getOrderById(int order_no) {
		return ORDER_SERVICE.findById(order_no);
	}

	// 검색 조건에 따른 발주 목록 조회
	public List<Ordering> searchOrders(String query) {
		return ORDER_SERVICE.searchByQuery(query);
	}

	// 삭제기능
	public void deleteOrder(int order_no) {
		ORDER_SERVICE.deleteById(order_no);
	}
}