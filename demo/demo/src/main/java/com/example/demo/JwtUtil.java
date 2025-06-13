package com.example.demo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration}")
	private long jwtExpirationMs;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(key).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
}
