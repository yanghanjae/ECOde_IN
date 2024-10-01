package com.project.ecodein.service;

import com.project.ecodein.repository.OrderingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.ecodein.dto.Ordering;

@Service
public class OrderingService {


	private final OrderingRepository ORDERING_REPOSITORY;

	public OrderingService(OrderingRepository orderingRepository) {

		this.ORDERING_REPOSITORY = orderingRepository;
	}

	// 페이지네이션 및 검색 기능 구현
	public Page<Ordering> getOrders(@Param("page") int page, @Param("query") String query,
                                    @Param("status") String status) {

        Sort sort = Sort.by(Sort.Direction.ASC, "order_no");

        if (query == null && status == null || status.equals("all")) {
            sort = Sort.by(Sort.Order.desc("orderNo")); // 최신 발주 번호 순으로 정렬
        }

		Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회

        // (전체검색)검색어 없고, 상태 체크 안한 경우
        if (query == null && status == null || status.equals("all")) {
            return ORDERING_REPOSITORY.findAll(pageable);
            // (키워드 검색) 검색어가 있는 경우
        } else if (query != null) {
            return ORDERING_REPOSITORY.searchByQuery(query, pageable);
            // (상태 검색) 상태코드가 있는 경우
        } else {
            byte statusble = 99;

            if (status.equals("progress")) {
                statusble = 1;
            } else {
                statusble = 0;
            }

            return ORDERING_REPOSITORY.findAllByIsDelivery(statusble, pageable);
        }
	}


	// (필터검색)_페이지네이션 및 검색 기능 구현
//	public Page<Ordering> getOrders(int page, String query, String status) {
//		Sort sort = Sort.by(Sort.Order.desc("orderNo")); // 최신 발주 번호 순으로 정렬
//		Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회
//
//		// 상태에 따른 조건 처리
//		if (status != null && !status.isEmpty()) {
//			switch (status) {
//				case "in-progress":
//					return ORDERING_REPOSITORY.findByIsDeliveryFalse(pageable); // 진행중인 발주
//				case "completed":
//					return ORDERING_REPOSITORY.findByIsDeliveryTrue(pageable); // 완료된 발주
//				case "all":
//				default:
//					return ORDERING_REPOSITORY.findAll(pageable); // 전체 발주
//			}
//		}
//
//		// 검색어가 없으면 전체 조회, 검색어가 있으면 조건 검색
//		if (query == null || query.isEmpty()) {
//			return ORDERING_REPOSITORY.findAll(pageable); // 검색어가 없을 때 전체 조회
//		} else {
//			return ORDERING_REPOSITORY.searchByQuery(query, pageable); // 검색어가 있을 때 조건 검색
//		}
//	}



	// 검색 조건에 따른 발주 목록 조회 (삭제 ? )
//	public Page<Ordering> searchOrders(String query) {
//		return ORDER_REPOSITORY.searchByQuery(query, pageable);
//	}

	// 삭제 기능
	public void deleteOrder(int orderNo) {
		ORDERING_REPOSITORY.deleteById(orderNo);
	}

}