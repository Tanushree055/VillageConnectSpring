package com.project.VillageConnectSpring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.VillageConnectSpring.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {

	@Query("SELECT p FROM Problem p WHERE p.user.villageName = :villageName")
	Page<Problem> findByVillageName(@Param("villageName") String villageName, Pageable pageable);


}
