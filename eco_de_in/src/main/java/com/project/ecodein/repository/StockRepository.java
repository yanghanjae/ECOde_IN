package com.project.ecodein.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	
	// 상품만 검색
	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE (s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = false) "
		+ "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material = false) ",
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE (s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = false) "
	        + "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material = false)" 
		,nativeQuery = true)
	public Page<Stock> findStockByKeyword (@Param(value = "search") String search, Pageable pageable);

	// 전부 검색
	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE s.stock_no LIKE CONCAT('%',:search,'%')  "
		+ "OR i.item_name LIKE CONCAT('%',:search,'%') ",
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE s.stock_no LIKE CONCAT('%',:search,'%')  "
	        + "OR i.item_name LIKE CONCAT('%',:search,'%') " 
		,nativeQuery = true)
	public Page<Stock> findAllStockByKeyword (String search, Pageable pageable);
	
	// 상품만 창고, 이름 검색
	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE ((s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = false) "
		+ "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material =false)) "
		+ "AND s.storage_no = :storage_no " ,
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE ((s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = false) "
	        + "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material = false)) "
	        + "AND s.storage_no = :storage_no " ,
	        nativeQuery = true)
	public Page<Stock> findyStockByKeywordStorage (@Param(value = "search") String search, 
		@Param(value = "storage_no") Integer storage_no, Pageable pageable);

	// 창고, 이름 검색
	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE (s.stock_no LIKE CONCAT('%',:search,'%')  "
		+ "OR i.item_name LIKE CONCAT('%',:search,'%')) "
		+ "AND s.storage_no = :storage_no " ,
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE (s.stock_no LIKE CONCAT('%',:search,'%')  "
	        + "OR i.item_name LIKE CONCAT('%',:search,'%')) "
	        + "AND s.storage_no = :storage_no " ,
	        nativeQuery = true)
	public Page<Stock> findAllStockByKeywordStorage (String search, Integer storage_no, Pageable pageable);

	
	// 재고 상세 정보
	@Query(value = "SELECT * FROM stock WHERE stock_no = :stock_no", nativeQuery = true)
	public Optional<Stock> findByStockNO (@Param(value = "stock_no") int stock_no);


}
