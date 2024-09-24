package com.project.ecodein.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Ordering;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Ordering, Integer> {

	//service
    @Query(value = "select * from ordering where order_no = :order_no", nativeQuery = true)
	public Optional<Ordering> findAll(int order_no);

}
