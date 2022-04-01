package com.aiwacs.spring.controllers;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiwacs.spring.common.HistoryUtils;
import com.aiwacs.spring.models.ERole;
import com.aiwacs.spring.models.Role;
import com.aiwacs.spring.models.User;
import com.aiwacs.spring.payload.request.LoginRequest;
import com.aiwacs.spring.payload.request.SignupRequest;
import com.aiwacs.spring.payload.response.JwtResponse;
import com.aiwacs.spring.payload.response.MessageResponse;
import com.aiwacs.spring.repository.HistoryRecordRepository;
import com.aiwacs.spring.repository.RoleRepository;
import com.aiwacs.spring.repository.UserRepository;
import com.aiwacs.spring.security.jwt.JwtUtils;
import com.aiwacs.spring.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/aiwacs")
// SecurityContextHolder ->" getContext() " 중간자 -> Authentication -> principal

public class AuthController {
	@Autowired AuthenticationManager authenticationManager;

	@Autowired UserRepository userRepository;

	@Autowired RoleRepository roleRepository;

	@Autowired PasswordEncoder encoder;

	@Autowired JwtUtils jwtUtils;
	
	@Autowired HttpServletRequest request;
	
	@Autowired HistoryRecordRepository historyRecordRepository;
	
//	@Autowired HistoryUtils historyUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws UnknownHostException {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
	    
	    System.out.println(loginRequest.getUsername());
	    System.out.println(loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())); 

		SecurityContextHolder.getContext().setAuthentication(authentication);  // 시큐리티 저장소인 SecurityContextHolder에 저장
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();   
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		String name = loginRequest.getUsername();
		HistoryUtils.login(name);


		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println("회원가입 시도");
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()  // 400 클라이언트 오류처리
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest() 
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();   // 

		if (strRoles == null) {
			
			Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
