package com.spring.rest_api.career_crafter.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.rest_api.career_crafter.service.MyUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * This class will override a method that will act as Filter.
 * 
 * OncePerRequestFilter; Every time a request for an API comes IN, Spring must
 * make it go thru this filter  
 * */
@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private MyUserService myUserService ;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	    try {
	        final String authorizationHeader = request.getHeader("Authorization");
	        System.out.println("Authorization Header: " + authorizationHeader);

	        String username = null;
	        String jwt = null;

	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            jwt = authorizationHeader.substring(7);
	            username = jwtUtil.extractUsername(jwt);
	        }

	        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	            UserDetails userDetails = this.myUserService.loadUserByUsername(username);
	            System.out.println("Extracted Username from Token: " +username);
	            System.out.println("Username from DB: " + userDetails.getUsername());

	            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	            else {
	                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	                response.getWriter().write("Access Denied: Invalid Token");
	                return;
	            }
	        }
	        filterChain.doFilter(request, response);
	    }
	    catch(Exception e) {
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.getWriter().write("Access Denied: " + e.getMessage());
	        return;
	    }
	}
}