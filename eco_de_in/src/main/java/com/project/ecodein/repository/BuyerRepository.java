package com.project.ecodein.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

	@Query (value = "select * from buyer where buyer_name = ? or buyer_number = ?", nativeQuery = true)
	public List<Buyer> findByBuyerNameOrBuyerNumber (String keyword);
	

}
