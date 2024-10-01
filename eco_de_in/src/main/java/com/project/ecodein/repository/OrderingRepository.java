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
    Page<Ordering> searchByQuery(@Param("query") String query, Pageable pageable_t);


    // (필터 검색)
//    // 진행중인 발주 조회
//    Page<Ordering> findByIsDeliveryFalse(Pageable pageable); // 진행중
//
//    // 완료된 발주 조회
//    Page<Ordering> findByIsDeliveryTrue(Pageable pageable); // 완료
//
//    // 전체 발주 조회
//    Page<Ordering> findAll(Pageable pageable); // 전체
}