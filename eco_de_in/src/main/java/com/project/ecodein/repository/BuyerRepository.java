package com.project.ecodein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Buyer;
import jakarta.transaction.Transactional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	// 검색 시 실행되는 코드
	@Query (value = "select * from buyer where buyer_status = :status and buyer_name like concat('%', :keyword, '%') or buyer_number like concat('%', :keyword, '%')", nativeQuery = true)
	public Page<Buyer> findByBuyerStatusAndBuyerNameOrBuyerNumber (@Param ("keyword") String keyword,
		@Param ("status") int status,
		Pageable pageable);

	// 기본 실행 코드(조회)
	@Query (value = "select * from buyer where buyer_status = :status", nativeQuery = true)
	public Page<Buyer> findByBuyerStatus (Pageable pageable, @Param ("status") int status);
	
	// 상태 수정
	@Query (value = "update buyer set buyer_status = 0 where buyer_code = :buyer_code", nativeQuery = true)
	@Modifying @Transactional
	public void updateBuyerStatusByBuyerCode (@Param ("buyer_code") Long buyer_code);


}
