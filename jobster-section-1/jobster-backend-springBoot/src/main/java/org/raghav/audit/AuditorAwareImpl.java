package org.raghav.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
    	//return Optional.of( Arrays.asList("john21", "jane22", "joe23").get(new Random().nextInt(3)) );
    	
		//Best-way of doing things
		if(SecurityContextHolder.getContext().getAuthentication() != null) {
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return Optional.of(username);
		}
		return null;
	}

}
