package org.raghav.security;

import org.raghav.entity.User;
import org.raghav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WebSecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	// DaoAuthenticationProvider is called below method by default
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User details not found for the user: " + username));

		System.out.println("user details =============> " + user);
		
		return user;
	}

}
