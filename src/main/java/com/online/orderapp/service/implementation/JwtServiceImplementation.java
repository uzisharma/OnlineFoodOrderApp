package com.online.orderapp.service.implementation;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.online.orderapp.service.JwtService;
import com.online.orderapp.util.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImplementation implements JwtService{
	
	private final SecretKey SECRET_KEY;
	private final long EXPIRATION_TIME = 1000 * 60 * 60; //1 hour
	
	public JwtServiceImplementation(@Value("${jwt.secret.key}") String secret) {
		this.SECRET_KEY= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		// TODO Auto-generated method stub
		
		Map<String,Object> claim = new HashMap<>();
		claim.put("role", userDetails.getAuthorities()
				.iterator()
				.next()
				.getAuthority());
		
		return Jwts.builder()
				.claims(claim)
				.subject(userDetails.getUsername())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SECRET_KEY)
				.compact();
	}

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}


	@Override
	public Role extractRole(String token) {
		// TODO Auto-generated method stub
		String role = extractClaim(token, claims->claims
				.get("role", String.class));
		
		if(role.startsWith("ROLE_")) {
			role = role.substring(5);
		}
		return Role.valueOf(role);
	}

	@Override
	public boolean validateToken(String token) {
		extractAllClaims(token);
		return !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		Date expiration = extractClaim(token, Claims::getExpiration);
		if(expiration.before(new Date())){
			return true;
		}
		return false;
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		// TODO Auto-generated method stub
		final Claims claim = extractAllClaims(token);
		return claimResolver.apply(claim);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser()
				.verifyWith(SECRET_KEY)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
