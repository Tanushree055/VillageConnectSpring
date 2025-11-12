package com.project.VillageConnectSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.VillageConnectSpring.model.User;

public interface RegistrationRepository extends JpaRepository<User,Integer>{

	String sql = "INSERT INTO users (firstname, lastname, email, villageName, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
    
	@Query("Select Count(u)>0 FROM User u WHERE u.email=:email")
	boolean existsByEmail(@Param("email") String email);
	
	User findByEmail(String email);
}
