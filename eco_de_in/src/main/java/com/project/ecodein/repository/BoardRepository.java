package com.project.ecodein.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ecodein.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	
	
}
