package com.project.VillageConnectSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.VillageConnectSpring.model.ProblemUpvote;

public interface ProblemUpvoteRepository extends JpaRepository<ProblemUpvote, Integer> {

	Optional<ProblemUpvote> findByUserIdAndProblemId(int userId,int problemId);
}
