package org.raghav.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequestDTO {
	
	@Size(min = 2, max = 50, message = "first name must be in size 2 to 50")
	private String firstName;

	private String lastName;

	@Email(message = "Please provide valid email id")
	private String email;

	@Size(min = 2, message = "Please choose strong password")
	private String password;

}
