package org.raghav.controller;

import org.raghav.dto.CreateUserRequestDTO;
import org.raghav.dto.CreateUserResponseDTO;
import org.raghav.dto.LoginRequestDTO;
import org.raghav.dto.LoginResponseDTO;
import org.raghav.security.ApplicationConstants;
import org.raghav.security.LoginService;
import org.raghav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/register")
	public ResponseEntity<CreateUserResponseDTO> register(@RequestBody @Valid CreateUserRequestDTO userDTO) {

		String email = userDTO.getEmail();
		if (userService.isExists(email))
			throw new RuntimeException("email already exist");

		// Validation
		// Binding Result

		CreateUserResponseDTO user = userService.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequest) {
		String jwt = "";
		LoginResponseDTO responseDTO = loginService.attemptLogin(loginRequest);
		return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER, responseDTO.getJwt()).body(responseDTO);
	}

}
