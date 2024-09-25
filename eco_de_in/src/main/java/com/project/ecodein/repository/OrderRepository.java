package com.project.ecodein.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Ordering;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Ordering, Integer> {

	// 1버전
//    @Query(value = "select * from ordering where order_no = :order_no", nativeQuery = true)
//	public Optional<Ordering> findByOrderNo(int order_no);

	// 2버전
	@Query(value = "SELECT * FROM ordering o " +
			"JOIN buyer b ON o.buyer_code = b.buyer_code " +
			"WHERE o.order_no LIKE CONCAT('%', :query, '%') OR b.buyer_name LIKE CONCAT('%', :query, '%')",
			nativeQuery = true)
	List<Ordering> searchByQuery(@Param("query") String query);


}