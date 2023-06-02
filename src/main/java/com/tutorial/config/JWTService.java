package com.tutorial.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private static final String SECRET_KEY = "5970337336763979244226452948404D635166546A576E5A7234743777217A25\r\n"
			+ "";

	public String extractUsername(String jwtToken) {

		return extractClaim(jwtToken, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims extractAllClaims = extractAllClaims(token);
		return claimResolver.apply(extractAllClaims);
	}

	public String generateToken(Map<String, Object> extractClaim, UserDetails userDetails) {
		return Jwts.builder().setClaims(extractClaim).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))// valid for 24 hrs + 1000 ms
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact(); // compact will generate and return a
																				// token
	}
	
	public boolean isValidToekn(String token, UserDetails userDetails) {
		// if the token belongs to this user
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
