
package com.spring.rest_api.career_crafter.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.spring.rest_api.career_crafter.repository.AuthRepository;
@Service
public class MyUserService  implements UserDetailsService{
	
	@Autowired
	private AuthRepository authRepository;
	
	@Override
 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 		return (UserDetails) authRepository.findByUsername(username);
}
}