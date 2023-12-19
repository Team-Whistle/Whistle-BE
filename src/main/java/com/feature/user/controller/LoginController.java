package com.feature.user.controller;

import com.core.security.service.UserDetailsImpl;
import com.core.utils.JMap;
import com.feature.user.dto.LoginRequest;
import com.feature.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class LoginController {

	private final LoginService loginService;



	@PostMapping("/login")
	public ResponseEntity<JMap> loginRequest(@RequestBody LoginRequest loginRequest) {

		final JMap result = loginService.getLoginResponse(loginRequest);

		return ResponseEntity.ok(result);
	}

	@PostMapping("/test")
	public ResponseEntity<JMap> test(@RequestBody LoginRequest loginRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {

		final JMap result = loginService.getTest(loginRequest, userDetails.getUser());

		return ResponseEntity.ok(result);
	}

}
