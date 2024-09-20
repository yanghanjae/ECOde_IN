package com.project.ecodein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ecodein.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	
}
