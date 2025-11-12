package com.project.VillageConnectSpring.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.project.VillageConnectSpring.model.Problem;
import com.project.VillageConnectSpring.model.ProblemUpvote;
import com.project.VillageConnectSpring.model.User;
import com.project.VillageConnectSpring.repository.ProblemRepository;
import com.project.VillageConnectSpring.repository.ProblemUpvoteRepository;

@Service
public class ProblemService {

	@Autowired
	ProblemRepository problemRepo; 
	
	@Autowired
	private ProblemUpvoteRepository upvoteRepo;
	
	public void saveProblem(Problem problem) {
	    problemRepo.save(problem);
	}
	
	public Page<Problem> getProblemsForVillage(String villageName,int page,int size){
		Pageable pageable=PageRequest.of(page-1,size);
		return problemRepo.findByVillageName(villageName, pageable);
	}
	
	public void toggleUpvote(int userId, int problemId) {
		Optional<ProblemUpvote> existing= upvoteRepo.findByUserIdAndProblemId(userId, problemId);
		Problem problem=problemRepo.findById(problemId).orElseThrow(()->new RuntimeException("Problem not found"));
		
		if(existing.isPresent()) {
			upvoteRepo.delete(existing.get());
			problem.setUpvotes(problem.getUpvotes()-1);
		}else {
			ProblemUpvote newUpvote=new ProblemUpvote();
			User user=new User();
			user.setId(userId);
			newUpvote.setUser(user);
			newUpvote.setProblem(problem);
			newUpvote.setUpvote(true);
			
			upvoteRepo.save(newUpvote);
			problem.setUpvotes(problem.getUpvotes()+1);
		}

        problemRepo.save(problem);//To save the changes done in upvote count just above
	}
}
