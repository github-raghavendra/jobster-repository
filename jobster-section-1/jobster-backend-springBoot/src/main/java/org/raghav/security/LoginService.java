package org.raghav.security;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.raghav.dto.LoginRequestDTO;
import org.raghav.dto.LoginResponseDTO;
import org.raghav.entity.User;
import org.raghav.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;

@Service
public class LoginService {

	@Autowired
	private Environment env;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	public LoginResponseDTO attemptLogin(LoginRequestDTO requestDTO) {

		String jwt = "";

		Authentication unAuthenticated = UsernamePasswordAuthenticationToken.unauthenticated(requestDTO.email(),
				requestDTO.password());

		Authentication authentication = authenticationManager.authenticate(unAuthenticated);

		if (null != authentication && authentication.isAuthenticated()) {

			String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
					ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

			SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

			jwt = jwtTokenUtils.generateJwtToken(authentication, secretKey);
		}

		String username = authentication.getName();

		System.out.println("\n ====== Authenticated user ==============>> " + username);
		// User user = userService.getUserByEmail(loginRequest.email());

		User user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("user does not exist!"));

		LoginResponseDTO responseDTO = new LoginResponseDTO();
		BeanUtils.copyProperties(user, responseDTO);

		responseDTO.setJwt(jwt);
		responseDTO.setCreatedAt(user.getCreatedAt());
		responseDTO.setUpdatedAt(user.getLastModifiedAt());

		return responseDTO;
	}
}
