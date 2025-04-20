package com.spring.rest_api.career_crafter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.rest_api.career_crafter.service.MyUserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserService myUserService;
	
	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(csrf->csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
					.requestMatchers("/api/auth/signup").permitAll()
	                .requestMatchers("/api/auth/token/generate").permitAll()
	                .requestMatchers("/api/job/all").permitAll()

	                // Login just returns logged in user, should be authenticated
	                .requestMatchers("/api/auth/login").authenticated()
	                .requestMatchers("/api/auth/user/details").authenticated()

	                // Role-based access
	                .requestMatchers("/api/hr/**").hasAuthority("HR")
	                .requestMatchers("/api/executive/**").hasAuthority("EXECUTIVE")
	                .requestMatchers("/api/job-seeker/**").hasAuthority("JOBSEEKER")
	                .requestMatchers("/api/assessment/all/{appId}").hasAuthority("JOBSEEKER")
	                
	                .requestMatchers("/api/instructor/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/company/add").hasAuthority("ADMIN")
	                .requestMatchers("/api/preference/add").hasAuthority("JOBSEEKER")
	                .requestMatchers("/api/company-review/**").hasAuthority("JOBSEEKER")
	                .requestMatchers("/api/job/add").hasAuthority("HR")
	                .requestMatchers("/api/application/add/{jsId}/{jobId}").hasAuthority("JOBSEEKER")
	                .requestMatchers("/api/application/count/total/{jsId}").hasAuthority("JOBSEEKER")
	                .requestMatchers("/api/application/count-by-status/{jsId}").hasAuthority("JOBSEEKER")
	                
	                
	                 
	                .requestMatchers("/api/certificate/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/assignment/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/category/**").hasAuthority("INSTRUCTOR")
	               .requestMatchers("/api/contents/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/modules/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/course/add").hasAuthority("INSTRUCTOR")
	               .requestMatchers("/api/instructor/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/course/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/category/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/contents/**").hasAuthority("INSTRUCTOR")
	                .requestMatchers("/api/modules/**").hasAuthority("INSTRUCTOR")
	                
	            
	               .requestMatchers("/api/reviews/**").hasAuthority("INSTRUCTOR")
	               
	               .requestMatchers("/api/enrollments/**").hasAuthority("INSTRUCTOR") 
	                
	               
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		;
		return http.build();
	}
	
	@Bean
	AuthenticationProvider getAuth() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passEncoder());
		dao.setUserDetailsService(myUserService);	
		return dao;
	}
	
	@Bean
	BCryptPasswordEncoder passEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		  return auth.getAuthenticationManager();
	 }
	//RBAC
}