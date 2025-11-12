package com.project.VillageConnectSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.VillageConnectSpring.model.User;
import com.project.VillageConnectSpring.repository.RegistrationRepository;

import jakarta.servlet.http.HttpSession;

@Controller
//@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	RegistrationRepository registerRepo;
	
	@GetMapping("/register")
    public String showRegistrationPage() {
		return "redirect:/html/register.html";
    }
	@PostMapping("/register")
	public String register(@RequestParam String firstname,
			@RequestParam String lastname,
			@RequestParam String email,
			@RequestParam String phone,
			@RequestParam String villageName,
			@RequestParam String password, 
			HttpSession session) {
		
		if(registerRepo.existsByEmail(email)) {
			return "redirect:/register?error=Email already exists";
		}
		User user=new User();
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setVillage(villageName);
		
		User savedUser=registerRepo.save(user);
		
		session.setAttribute("id", savedUser.getId());
		session.setAttribute("villageName", savedUser.getVillage());
		System.out.println("Village stored in session (after register): " + savedUser.getVillage());
	    session.setAttribute("email", savedUser.getEmail());
	    
		return "redirect:/home.html";
	}
}
