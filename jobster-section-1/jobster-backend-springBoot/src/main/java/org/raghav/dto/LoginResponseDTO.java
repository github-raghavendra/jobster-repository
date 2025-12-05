package org.raghav.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginResponseDTO {
	
	private String jwt;
	
	private String firstName;

	private String lastName;

	// username is email
	private String email;
	
	private boolean isBlocked;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	//private List<AuthorityDTO> authorities;

}
