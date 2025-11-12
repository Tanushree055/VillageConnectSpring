package com.project.VillageConnectSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
	    // 🛑 WARNING: This is for DEVELOPMENT/TESTING ONLY! 
	    // It's highly insecure for production.
	    return NoOpPasswordEncoder.getInstance(); 
	}
	
	@Autowired
	private CustomLoginSuccessHandler customLoginSuccessHandler;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Generally acceptable for Stateless/API, but often required for form login

            .authorizeHttpRequests(auth -> auth
                // ✅ Allow static resources and public pages (e.g., /login.html, /css/style.css)
                .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll() 
                
                // ✅ Allow the public HTML pages (now at the root of /static)
                .requestMatchers("/login.html", "/register.html", "/login", "/logout").permitAll()

                // 🛑 EVERYTHING ELSE REQUIRES AUTHENTICATION (including /home.html)
                .anyRequest().authenticated()
            )


            .formLogin(login -> login
                    .loginPage("/login.html")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(customLoginSuccessHandler)  // ✅ added this line
                    .failureUrl("/login.html?error=true")
                    .permitAll()
                )

            // ✅ Logout Configuration
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // ✅ Session Management
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // Creates a session if one doesn't exist
            );

        return http.build();
    }
}
