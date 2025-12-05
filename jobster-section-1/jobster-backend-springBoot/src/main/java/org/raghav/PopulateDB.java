package org.raghav;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import org.raghav.entity.Authority;
import org.raghav.entity.User;
import org.raghav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class PopulateDB implements ApplicationRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User u1 = new User();
		u1.setId(new SecureRandom().nextLong(1111, 9999));
		u1.setFirstName("test");
		u1.setLastName("test");
		u1.setEmail("test@test.com");
		u1.setPassword(encoder.encode("test@246424"));
		
		Set<Authority> authorities = new HashSet<>();
		
		Authority a1 = new Authority();
		a1.setName("ROLE_USER");
		a1.setUser(u1);
		
		authorities.add(a1);
		u1.setAuthorities(authorities);
		userRepository.save(u1);
	}

}
