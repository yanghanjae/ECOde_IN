package com.project.ecodein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Ordering;


@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Integer> {

    // 검색 쿼리
    @Query(value = "select o.*, b.buyer_name from ordering o " +
            "left join buyer b on o.buyer_code = b.buyer_code " +
            "where o.order_no = :query or b.buyer_name " +
            "like concat('%', :query, '%')", nativeQuery = true)
    Page<Ordering> searchByQuery(@Param("query") String query, Pageable pageable);

    // 상태검색 쿼리
    @Query(value = "select o.*, b.buyer_name from ordering o " +
                    "left join buyer b on o.buyer_code = b.buyer_code " +
                    "where o.is_delivery = :statusble", nativeQuery = true)
    Page<Ordering> findAllByIsDelivery (@Param("statusble") byte statusble, Pageable pageable);

    // 상품 등록 검색 기능 추가
    @Query(value = "SELECT o.*, i.item_name, s.quantity FROM ordering o " +
            "LEFT JOIN item i ON o.item_no = i.item_no " +
            "LEFT JOIN stock s ON o.stock_no = s.stock_no " +
            "WHERE i.item_name LIKE CONCAT('%', :itemName, '%')", nativeQuery = true)
    Page<Ordering> searchOrdersByItemName(@Param("itemName") String itemName, Pageable pageable);

}