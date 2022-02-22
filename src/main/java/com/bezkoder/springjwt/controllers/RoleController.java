package com.bezkoder.springjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)  // 3600초 동안 preflight 결과를 캐쉬 저장
@RestController
@RequestMapping("/api/test")
public class RoleController {
	@GetMapping("/all")
	public String allAccess() {
		return "메인 페이지";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('ADMIN')")
	public String userAccess() {
		return "관리 페이지";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "중재자 페이지";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "관리자 페이지";
	}

	
	@GetMapping("/modal")
	public String test() {
		return "스프링에서 넘어온 테스트";
	}
}
