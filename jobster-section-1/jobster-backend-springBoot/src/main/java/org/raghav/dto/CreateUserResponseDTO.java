package org.raghav.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateUserResponseDTO {

	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;
	
}
