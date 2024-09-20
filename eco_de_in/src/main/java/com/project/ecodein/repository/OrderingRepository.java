package com.project.ecodein.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Ordering;

@Repository
public interface OrderingRepository extends JpaRepository<Ordering, Integer> {

	//service
	
}
