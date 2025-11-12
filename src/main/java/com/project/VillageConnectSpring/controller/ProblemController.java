package com.project.VillageConnectSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.VillageConnectSpring.model.Problem;
import com.project.VillageConnectSpring.service.ProblemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProblemController {

	@Autowired
	ProblemService problemService; 
	
	@PostMapping("/problems")
	public String problemPosting(@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String email,
			@RequestParam String subject,
			@RequestParam String description,
			HttpSession session) {
		Problem problem=new Problem();
		problem.setFirstname(firstname);
		problem.setLastname(lastname);
		problem.setEmail(email);
		problem.setSubject(subject);
		problem.setDescription(description);
		
		problemService.saveProblem(problem);
		return "problems";
	}
	
	@GetMapping("/problems")
	public String getProblems(HttpSession session,
            @RequestParam(defaultValue = "1") int page,
            Model model) {

		String villageName=(String)session.getAttribute("villageName");
        int pageSize = 3;
        Page<Problem> problemsPage = problemService.getProblemsForVillage(villageName, page, pageSize);

        model.addAttribute("problems", problemsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", problemsPage.getTotalPages());

        return "problems";
    }
	@PostMapping("/problems/upvote")
    public String upvoteProblem(@RequestParam int problemId, HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId"); // assuming login sets this
        if (userId == null) {
        	return "redirect:/login.html";
        }

        problemService.toggleUpvote(userId, problemId);
        return "problems";
    }
}
