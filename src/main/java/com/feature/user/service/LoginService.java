package com.feature.user.service;

import com.core.security.jwt.JwtTokenManager;
import com.core.utils.JMap;
import com.feature.user.domain.UserRole;
import com.feature.user.dto.*;
import com.feature.user.repo.UserRepository;
import com.feature.user.repo.utils.KakaoUserInfo;
import com.feature.user.domain.User;
import com.core.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

	private final UserService userService;
	private final JwtTokenManager jwtTokenManager;
	private final AuthenticationManager authenticationManager;
	private final KakaoUserInfo kakaoUserInfo;
	private final UserRepository userRepository;
	private final UserDetailsService userDetailsService;


	public JMap getLoginResponse(LoginRequest loginRequest) {

		JMap result = new JMap();

		try {
			final String Token = loginRequest.getToken();
			KakaoUserInfoResponse kakaoUserInfoResponse = null;
			String username = null;

			if ("kakao".equals(loginRequest.getSnsType())) {
				kakaoUserInfoResponse = kakaoUserInfo.getUserInfo(Token);
				username = kakaoUserInfoResponse.getKakao_account().getEmail();
			}

			User userCheck = userRepository.findByUserEmail(username);

			if (userCheck == null) {
				User user = User.builder()
						.userEmail(username)
						.userType(UserRole.USER)
						.password("")
						.build();
				userRepository.save(user);
			}

			User user = userRepository.findByUserEmail(kakaoUserInfoResponse.getKakao_account().getEmail());

//			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			// 직접 Authentication 객체를 생성
//			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//
//			// AuthenticationManager에 전달하여 인증 수행
//			Authentication authenticatedAuthentication = authenticationManager.authenticate(authentication);

			final String token = jwtTokenManager.generateToken(user);

			log.info("{} has successfully logged in!", user.getUserEmail());
			result.put("rsltCd", "00");
			result.put("rsltMsg", "Success");
			result.put("Token", token);
		} catch (Exception e) {
			e.printStackTrace();
			// 추가적인 예외 처리 또는 로깅 등을 수행할 수 있습니다.
		}

		return result;
	}

	public JMap getTest(LoginRequest loginRequest, User user) {
		JMap result = new JMap();

		result.put("userEmail",user.getUserEmail());
		return result;
	}
}