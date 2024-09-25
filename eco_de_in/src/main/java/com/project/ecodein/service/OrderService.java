package com.project.ecodein.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecodein.dto.Ordering;
import com.project.ecodein.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	// 전체 발주 목록 조회
	public List<Ordering> getAllOrders() {
		return orderRepository.findAll();
	}

	// 특정 발주 번호로 발주 검색
	public Optional<Ordering> getOrderById(int order_no) {
		return orderRepository.findById(order_no);
	}

	// 검색 조건에 따른 발주 목록 조회
	public List<Ordering> searchOrders(String query) {
		return orderRepository.searchByQuery(query);
	}
}