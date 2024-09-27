package com.project.ecodein.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query(value = "SELECT * "
		+ "FROM item "
		+ "WHERE is_material = false "
		+ "AND (item_no like CONCAT('%', :search, '%') OR item_name like CONCAT('%', :search, '%'))", nativeQuery = true)
	Page<Item> searchItem (@Param(value = "search") String search, Pageable pageable);

}
