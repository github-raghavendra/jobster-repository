package org.raghav.repository;

import java.util.Optional;

import org.raghav.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findById(Long id);
	
	public Optional<User> findByEmail(String email);
		
	public boolean existsByEmail(String email);
	
}
