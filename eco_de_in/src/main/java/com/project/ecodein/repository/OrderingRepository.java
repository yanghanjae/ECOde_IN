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

    // 1버전
//    @Query(value = "select * from ordering where order_no = :order_no", nativeQuery = true)
//	public Optional<Ordering> findByOrderNo(int order_no);

    // 검색 쿼리
//    @Query(value = "select * from ordering o join buyer b on o.buyer_code = b.buyer_code" +
//                    " where order_no like concat('%', :query, '%')" +
//                    " or buyer_name like concat('%', :query, '%')",
//            nativeQuery = true)
//     List<Ordering> searchByQuery(@Param("query") String query);
//    @Query(value = "select o.*, b.* from ordering o join buyer b on o.buyer_code = b.buyer_code " +
//            "where order_no = concat('%', :query, '%') or " +
//            "buyer_name like concat('%', :query, '%') ", nativeQuery = true)
//    Page<Ordering> searchByQuery(@Param("query") String query, Pageable pageable);
    @Query(value = "select o.*, b.buyer_name from ordering o left join buyer b on o.buyer_code = b.buyer_code where o.order_no = :query or b.buyer_name like concat('%', :query, '%')", nativeQuery = true)
    Page<Ordering> searchByQuery(@Param("query") String query, Pageable pageable_t);


//    // 기본 페이징 쿼리
//    Page<Ordering> findAll(Pageable pageable);
    }

