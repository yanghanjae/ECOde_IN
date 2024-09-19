package com.project.ecodein.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

	@Query("SELECT u "
		+ "FROM Admin u "
		+ "WHERE u.admin_id LIKE :admin_id")
	Optional<Admin> findByAdminId (@Param("admin_id") String admin_id);
	
}
