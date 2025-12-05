package org.raghav.security;

import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtils {

	public String generateJwtToken(Authentication authentication, SecretKey secretKey) {

		String jwt = null;

		jwt = Jwts
				.builder()
				.issuer("jobster.com")
				.subject("JWT Token")
				.claim("username", authentication.getName())
				.claim("authorities",
				authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(",")))
				.issuedAt(new java.util.Date())
				.expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
				.signWith(secretKey)
				.compact();

		return jwt;

	}

}
