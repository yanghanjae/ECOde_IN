package com.project.ecodein.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.User;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("SELECT u "
		+ "FROM User u "
		+ "WHERE u.user_id LIKE :user_id")
	Optional<User> findByUserId (@Param("user_id") String user_id);
	
	@Query("SELECT u "
		+ "FROM User u "
		+ "WHERE u.user_email LIKE :user_email")
	Optional<User> findByUserEmail (@Param("user_email") String user_email);

}
