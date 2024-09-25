package com.project.ecodein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE (s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material) "
		+ "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material =:is_material) ",
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE (s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material) "
	        + "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material)" 
		,nativeQuery = true)
	public Page<Stock> findStockByKeyword (@Param(value = "search") String search, 
		@Param(value = "is_material") boolean is_material, Pageable pageable);

	@Query(value = "SELECT s.* "
		+ "FROM stock s "
		+ "LEFT JOIN item i "
		+ "ON s.item_no = i.item_no "
		+ "WHERE ((s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material) "
		+ "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material =:is_material)) "
		+ "AND s.storage_no = :storage_no " ,
		countQuery = "SELECT count(*) "
	        + "FROM stock s "
	        + "LEFT JOIN item i "
	        + "ON s.item_no = i.item_no "
	        + "WHERE ((s.stock_no LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material) "
	        + "OR (i.item_name LIKE CONCAT('%',:search,'%') AND i.is_material = :is_material)) "
	        + "AND s.storage_no = :storage_no " ,
	        nativeQuery = true)
	public Page<Stock> findyStockByKeywordStorage (@Param(value = "search") String search, 
		@Param(value = "is_material") boolean is_material,
		@Param(value = "storage_no") Integer storage_no, Pageable pageable);

}
