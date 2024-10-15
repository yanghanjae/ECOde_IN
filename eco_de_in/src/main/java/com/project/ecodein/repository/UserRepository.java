package com.project.ecodein.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String>{

	@Query("SELECT u "
		+ "FROM User u "
		+ "WHERE u.user_id LIKE :user_id")
	Optional<User> findByUserId (@Param("user_id") String user_id);
	
	@Query("SELECT u "
		+ "FROM User u "
		+ "WHERE u.user_email LIKE :user_email")
	Optional<User> findByUserEmail (@Param("user_email") String user_email);

    @Modifying
    @Transactional
    @Query(value = "update user set user_password = :newPassword where user_id like :user_id", nativeQuery = true)
    Integer updatePassword(@Param("user_id") String user_id, @Param("newPassword") String newPassword);

    @Query(value = "select * from user where buyer_code = :buyer_code", nativeQuery = true)
    List<User> findAllByBuyerCode (int buyer_code);
}
