package com.project.VillageConnectSpring.service; // Use an appropriate package name

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.VillageConnectSpring.model.User; // Assuming your JPA entity is named User
import com.project.VillageConnectSpring.repository.LoginRepository;

import java.util.Collections; // Used for the empty list of authorities

@Service // Marks this class as a Spring component for detection
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    private final LoginRepository loginRepository;

    // Use constructor injection for the repository
    
    public CustomUserDetailsService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    // This method is called by Spring Security when a user attempts to log in
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Fetch User object from your database
        User user = loginRepository.findByEmail(email);

        if (user == null) {
            // Throw the required exception if the user is not found
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // 2. Convert your custom User object into a Spring Security UserDetails object
        // NOTE: The password retrieved here MUST be the hashed (BCrypt) password 
        // stored in your database.
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),                 // Username (email in your case)
            user.getPassword(),              // Hashed password from the database
            Collections.emptyList()          // List of GrantedAuthorities (roles/permissions)
        );
    }
}