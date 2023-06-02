package com.tutorial.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tutorial.config.JWTService;
import com.tutorial.controller.AuthenticationRequest;
import com.tutorial.controller.AuthenticationResponse;
import com.tutorial.controller.RegisterRequest;
import com.tutorial.entities.Role;
import com.tutorial.entities.User;
import com.tutorial.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;

	private final PasswordEncoder encoder;

	private final JWTService jwtService;

	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setLogin(request.getLogin());
		user.setName(request.getFirstname() + " " + request.getLastname());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole(Role.USER);
		repository.save(user);
		String token = jwtService.generateToken(user);
		System.out.println("register >> "+token);
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(token);
		return response;
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
		try {
			User user = repository.findByLogin(request.getLogin());
			String token = jwtService.generateToken(user);
			AuthenticationResponse response = new AuthenticationResponse();
			response.setToken(token);
			return response;
		}catch (BadCredentialsException e) {
			throw new BadCredentialsException(request.getLogin()+" does not exist");
		}
		
//		String token = jwtService.generateToken(user);
//		AuthenticationResponse response = new AuthenticationResponse();
//		response.setToken(token);
//		return response;
	}
}
