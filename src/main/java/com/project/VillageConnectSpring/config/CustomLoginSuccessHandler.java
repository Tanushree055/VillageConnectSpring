package com.project.VillageConnectSpring.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.project.VillageConnectSpring.model.User;
import com.project.VillageConnectSpring.repository.RegistrationRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
    private RegistrationRepository registerRepo;
	
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request,
	                                        HttpServletResponse response,
	                                        Authentication authentication)
	            throws IOException, ServletException {
		 String userEmail=authentication.getName();
		 User user = registerRepo.findByEmail(userEmail);
		 
		 if(user != null) {
			 HttpSession session=request.getSession();
			 session.setAttribute("id", user.getId());
			 session.setAttribute("email", user.getEmail());
			 session.setAttribute("villageName", user.getVillage());
			 System.out.println("✅ Village stored in session after login: " + user.getVillage());
		 }
		 response.sendRedirect(request.getContextPath() + "/home.html");

	 }
}
	

