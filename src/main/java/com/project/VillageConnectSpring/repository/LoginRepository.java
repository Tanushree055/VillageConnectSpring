package com.project.VillageConnectSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VillageConnectSpring.model.User;

public interface LoginRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
