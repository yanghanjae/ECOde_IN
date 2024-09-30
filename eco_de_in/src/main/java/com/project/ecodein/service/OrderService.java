package com.project.ecodein.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.ecodein.dto.Ordering;
import com.project.ecodein.repository.OrderRepository;

@Service
public class OrderService {


	private final OrderRepository ORDER_REPOSITORY;

	public OrderService(OrderRepository orderRepository) {
		this.ORDER_REPOSITORY = orderRepository;
	}

	// 페이지네이션 및 검색 기능 구현
	public Page<Ordering> getOrders(int page, String query) {
		Sort sort = Sort.by(Sort.Order.desc("order_no")); // 최신 발주 번호 순으로 정렬
		Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회

		// 검색어가 없으면 전체 조회, 검색어가 있으면 조건 검색
		if (query == null || query.isEmpty()) {
			return ORDER_REPOSITORY.findAll(pageable); // 검색어가 없을 때 전체 조회
		} else {
			return ORDER_REPOSITORY.searchByQuery(query, pageable); // 검색어가 있을 때 조건 검색
		}
	}

	// 전체 발주 목록 조회
	public List<Ordering> getAllOrders() {
		return ORDER_REPOSITORY.findAll();
	}

	// 검색 조건에 따른 발주 목록 조회
//	public Page<Ordering> searchOrders(String query) {
//		return ORDER_REPOSITORY.searchByQuery(query, pageable);
//	}

	// 삭제 기능
//	public void deleteOrder(int order_no) {
//		ORDER_REPOSITORY.deleteById(order_no);
//	}

}