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
				.requestMatchers("/api/instructor/getProfile").authenticated()
				.requestMatchers("/api/instructor/dashboard/metrics").authenticated()
				.requestMatchers("/api/instructor/update/{insId}").authenticated()
				.requestMatchers("/api/instructor/delete/profile/{insId}").authenticated()
				.requestMatchers("/api/instructor/image/upload/{Insid}").authenticated()
				.requestMatchers("api/instructor/uploads/{filename:.+}").permitAll()
				.requestMatchers("/api/course/getAll")
				.authenticated().requestMatchers("/api/course/add").authenticated()
				.requestMatchers("/api/course/delete/{courseId}").authenticated()
				.requestMatchers("/api/course/update/{courseId}").authenticated()
				.requestMatchers("/api/course/get/{courseId}").authenticated().requestMatchers("/api/modules/**")
				.authenticated().requestMatchers("/api/course/getAll").authenticated()
				.requestMatchers("/api/course/courses/search").authenticated().requestMatchers("/api/reviews/getAll")
				.authenticated().requestMatchers("/api/enrollments/**").authenticated()
				.requestMatchers("/api/certificates/**").authenticated()
				.requestMatchers("/api/modules/**").authenticated()
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