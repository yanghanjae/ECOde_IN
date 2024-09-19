package com.project.ecodein.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
=======
>>>>>>> 1677ce76b4ad9895d4cf2aabe632bb40c7b61384
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
<<<<<<< HEAD

	@Query (value = "select * from buyer where buyer_name = ? or buyer_number = ?", nativeQuery = true)
	public List<Buyer> findByBuyerNameOrBuyerNumber (String keyword);

=======
	
	List<Buyer> findAll();
		
>>>>>>> 1677ce76b4ad9895d4cf2aabe632bb40c7b61384
}
