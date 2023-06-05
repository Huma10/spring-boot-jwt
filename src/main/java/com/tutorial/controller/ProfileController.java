package com.tutorial.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.dto.UserDTO;
import com.tutorial.services.UserService;
import com.tutorial.util.ConstantFile;
import com.tutorial.util.Response;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProfileController {
	
	private final UserService userService;

	@GetMapping("/view-profile")
	public Response viewProfileInfo(HttpServletRequest request) {
		Response response = new Response();
		Principal userPrincipal = request.getUserPrincipal();
		UserDTO profileData = userService.getProfileData(userPrincipal.getName());
		response.setData(profileData); // got login detail
		response.setMessage(ConstantFile.VIEW_PROFILE);
		response.setStatus(true);
		return response;
	}
	
}
