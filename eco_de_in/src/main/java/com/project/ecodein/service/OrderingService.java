package com.project.ecodein.service;


import com.project.ecodein.dto.*;
import com.project.ecodein.repository.OrderDetailRepository;
import com.project.ecodein.repository.OrderingRepository;
import com.project.ecodein.repository.StockRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class OrderingService {


	private final OrderingRepository ORDERING_REPOSITORY;
    private final StockRepository STOCK_REPOSITORY;
    private final OrderDetailRepository ORDER_DETAIL_REPOSITORY;

    public OrderingService(OrderingRepository orderingRepository,
                           StockRepository stockRepository,
                           OrderDetailRepository orderDetailRepository) {
        this.ORDERING_REPOSITORY = orderingRepository;
        this.STOCK_REPOSITORY = stockRepository;
        this.ORDER_DETAIL_REPOSITORY = orderDetailRepository;
    }

    // mainPage에서 사용할 메서드
    public List<Ordering> getOrderings() {
        Sort sort = Sort.by(Sort.Direction.DESC, "orderNo");
        return ORDERING_REPOSITORY.findAll(sort);
    }

	// 페이지네이션 및 검색 기능 구현
	public Page<Ordering> getOrders(@Param("page") int page, @Param("query") String query,
                                    @Param("status") String status) {

        // (전체검색)검색어 없고, 상태 체크 안한 경우
        if (query == null && status.equals("all")) {
            System.out.printf("검색어/상태값 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("orderNo"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort);
            return ORDERING_REPOSITORY.findAll(pageable);
            // (키워드 검색) 검색어가 있는 경우
        } else if (query != null) {
            System.out.printf("상태값 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("order_no"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort);
            return ORDERING_REPOSITORY.searchByQuery(query, pageable);
            // (상태 검색) 상태코드가 있는 경우
        } else {
            System.out.printf("검색어 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("order_no"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort);

            byte statusble = 2;

            if (status.equals("progress")) {
                statusble = 0;
            } else {
                statusble = 1;
            }

            return ORDERING_REPOSITORY.findAllByIsDelivery(statusble, pageable);
        }
	}

	// 삭제 기능
	public void deleteOrder(int orderNo) {
		ORDERING_REPOSITORY.deleteById(orderNo);
	}

    // 발주등록
    public void addOrder(OrderPoolDTO orderPool) {
        //Ordering ordering = ORDERING_REPOSITORY.addsave(orderPool.getBuyer_code(), orderPool.getUser_id(), orderPool.getDue_date());
        Ordering ordering = new Ordering();
        ordering.setBuyer_code(new Buyer((long) orderPool.getBuyer_code()));
        ordering.setUser_id(new User(orderPool.getUser_id()));
        ordering.setDue_date(orderPool.getDue_date());
        ordering.setOrder_date(Date.valueOf(LocalDate.now()));
        Ordering order = ORDERING_REPOSITORY.save(ordering);
        System.out.println("order" + order);
    }

    // 발주등록_Stock을 이름으로 검색하는 메서드 추가 ???
    public List<Stock> searchStocksByName(String name) {

        return STOCK_REPOSITORY.orderFindAllStock(name);
    }

    // 발주등록_상품등록 ???
    public void registerOrder(List<Integer> productIds, List<Integer> quantities) {

        for (int i = 0; i < productIds.size(); i++) {
            int productId = productIds.get(i);
            int quantity = quantities.get(i);

            Stock stock = STOCK_REPOSITORY.findById(productId).orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        }
    }

    // 발주상세
    public void findById(int orderNo) {

    }
}