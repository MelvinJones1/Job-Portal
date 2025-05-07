package com.spring.rest_api.career_crafter.config;



import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
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
		.cors(withDefaults())
		.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/auth/signup").permitAll().requestMatchers("/api/auth/token/generate").permitAll()
				.requestMatchers("/api/auth/login").authenticated().requestMatchers("/api/auth/user/details")
				.authenticated().requestMatchers("/api/instructor/add").permitAll()
				.requestMatchers("/api/instructor/getProfile").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/instructor/dashboard/metrics").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/instructor/update/{insId}").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/instructor/delete/profile/{insId}").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/instructor/image/upload/{Insid}").hasAuthority("INSTRUCTOR")
				.requestMatchers("api/instructor/uploads/{filename}").permitAll()
				.requestMatchers("/api/course/getAll")
				.hasAuthority("INSTRUCTOR").requestMatchers("/api/course/add").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/course/delete/{courseId}").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/course/update/{courseId}").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/course/get/{courseId}").hasAuthority("INSTRUCTOR").requestMatchers("/api/modules/**")
				.hasAuthority("INSTRUCTOR").requestMatchers("/api/course/getAll").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/course/courses/search").hasAuthority("INSTRUCTOR").requestMatchers("/api/reviews/getAll")
				.hasAuthority("INSTRUCTOR").requestMatchers("/api/enrollments/**").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/certificates/**").hasAuthority("INSTRUCTOR")
				.requestMatchers("/api/modules/**").hasAuthority("INSTRUCTOR")
				.anyRequest().permitAll()

		).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
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
	BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	    configuration.setAllowedHeaders(List.of("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	// RBAC
}