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

	// 전체 검색(폐업 제외)
	@Query ("SELECT b FROM Buyer b WHERE b.buyer_status = true ")
	public Page<Buyer> findAllAcitve (Pageable pageable);
	
	// 상호명으로 검색(폐업 제외)
	@Query ("SELECT b FROM Buyer b WHERE (b.buyer_name LIKE CONCAT('%',:buyer_name,'%')) AND b.buyer_status = true ")
	public Page<Buyer> findByBuyerNameActive (@Param("buyer_name") String buyer_name, Pageable pageable);

	// 거래처 정보 수정
	@Modifying
	@Query (value = "update buyer b " +
                        "set b.buyer_name = :buyer_name, " +
                            "b.buyer_agent = :buyer_agent, " +
                            "b.buyer_number = :buyer_number, " +
                            "b.buyer_tel = :buyer_tel, " +
                            "b.buyer_address = :buyer_address" +
                       " where b.buyer_code = :buyer_code", nativeQuery = true)
	public void updateBuyer (@Param("buyer_code") Long buyer_code, 
							 @Param("buyer_name") String buyer_name, 
							 @Param("buyer_agent") String buyer_agent, 
							 @Param("buyer_number") String buyer_number,
							 @Param("buyer_tel") String buyer_tel,
							 @Param("buyer_address") String buyer_address);

}
