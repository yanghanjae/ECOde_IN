package com.project.ecodein.service;

import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.ItemRepository;
import com.project.ecodein.repository.OrderingRepository;
import com.project.ecodein.repository.StockRepository;
import com.project.ecodein.repository.StorageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.ecodein.dto.Ordering;

import java.util.List;
import java.util.Optional;

@Service
public class OrderingService {


	private final OrderingRepository ORDERING_REPOSITORY;
    private final StockRepository STOCK_REPOSITORY;
    private final StorageRepository STORAGE_REPOSITORY;
    private final ItemRepository ITEM_REPOSITORY;

    public OrderingService(OrderingRepository orderingRepository, StockRepository stockRepository,
                           StorageRepository storageRepository, ItemRepository itemRepository) {
        this.ORDERING_REPOSITORY = orderingRepository;
        this.STOCK_REPOSITORY = stockRepository;
        this.STORAGE_REPOSITORY = storageRepository;
        this.ITEM_REPOSITORY = itemRepository;
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
        if (query == null && status == null) {
            System.out.printf("검색어/상태값 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("orderNo"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회
            return ORDERING_REPOSITORY.findAll(pageable);
            // (키워드 검색) 검색어가 있는 경우
        } else if (query != null) {
            System.out.printf("상태값 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("order_no"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회
            return ORDERING_REPOSITORY.searchByQuery(query, pageable);
            // (상태 검색) 상태코드가 있는 경우
        } else {
            System.out.printf("검색어 없음 - query : %s%n, status : %s%n", query, status);
            Sort sort = Sort.by(Sort.Order.desc("order_no"));
            Pageable pageable = PageRequest.of(page - 1, 10, sort); // 페이지당 10개씩 조회

            byte statusble = 2;

            if (status.equals("progress")) {
                statusble = 1;
            } else {
                statusble = 0;
            }

            return ORDERING_REPOSITORY.findAllByIsDelivery(statusble, pageable);
        }
	}

	// 삭제 기능
	public void deleteOrder(int orderNo) {
		ORDERING_REPOSITORY.deleteById(orderNo);
	}

    // 재고 추가 및 업데이트
    public void addStock(int item_no, int storage_no, int quantity) {
        Optional<Stock> stock = STOCK_REPOSITORY.findStock(item_no, storage_no);
        if (stock.isPresent()) {
            STOCK_REPOSITORY.updateStock(stock.get().getStockNo(), stock.get().getQuantity() + quantity);
        } else {
            STOCK_REPOSITORY.addStock(item_no, storage_no, quantity);
        }
    }

    // 재고 조회
    public List<Storage> findAllStorage() {
        return STORAGE_REPOSITORY.findAllStorage();
    }

    public Optional<Stock> findByStockNo(int stock_no) {
        return STOCK_REPOSITORY.findByStockNO(stock_no);
    }

    public void editStock(int editStockNo, int editQuantity) {
        STOCK_REPOSITORY.updateStock(editStockNo, editQuantity);
    }

    public Page<Item> searchStocks(String search, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("item_name").ascending());
        if (search == null || search.isEmpty()) {
            search = "";
        }
        return ITEM_REPOSITORY.searchItem(search, pageable);
    }

    public Page<Storage> searchStorages(String search, int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("storage_name").ascending());
        if (search == null || search.isEmpty()) {
            search = "";
        }
        return STORAGE_REPOSITORY.findAllByStorageNameOrStorageSite(search, pageable);
    }

}