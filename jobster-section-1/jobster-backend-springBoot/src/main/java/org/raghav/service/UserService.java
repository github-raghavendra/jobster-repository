package org.raghav.service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import org.raghav.dto.CreateUserRequestDTO;
import org.raghav.dto.CreateUserResponseDTO;
import org.raghav.entity.Authority;
import org.raghav.entity.User;
import org.raghav.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public boolean isExists(String email) {
		return userRepository.existsByEmail(email);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("email not exits!"));
	}

	@Transactional
	public CreateUserResponseDTO createUser(CreateUserRequestDTO DTO) {
		User user = new User();
		user.setId(new SecureRandom().nextLong(1111, 9999));
		BeanUtils.copyProperties(DTO, user);
		user.setPassword(passwordEncoder.encode(DTO.getPassword()));

		Authority role_User = new Authority();
		role_User.setName("ROLE_USER");
		role_User.setUser(user);

		Set<Authority> authorities = new HashSet<>();
		authorities.add(role_User);

		user.setAuthorities(authorities);

		User savedUser = userRepository.save(user);

		System.out.println("savedCustomer=" + savedUser);

		log.info("savedCustomer data is {}", savedUser);

		CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();
		BeanUtils.copyProperties(savedUser, responseDTO);
		responseDTO.setUserId(user.getId().toString());
		responseDTO.setAuthorities(savedUser.getAuthorities());
		return responseDTO;
	}
	
}
