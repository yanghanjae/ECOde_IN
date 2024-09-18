package com.project.ecodein.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
	
	List<Buyer> findAll();
		
}
