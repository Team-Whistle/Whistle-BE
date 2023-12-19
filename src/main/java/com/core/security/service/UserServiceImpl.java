package com.core.security.service;

import com.feature.user.domain.User;
import com.feature.user.domain.UserRole;
import com.feature.user.repo.UserRepository;
import com.feature.user.dto.AuthenticatedUserDto;
import com.feature.user.dto.RegistrationRequest;
import com.feature.user.dto.RegistrationResponse;
import com.feature.user.repo.UserMapper;
import com.core.utils.GeneralMessageAccessor;
import com.feature.user.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public User findByUserEmail(String username) {

		return userRepository.findByUserEmail(username);
	}

	@Override
	public RegistrationResponse registration(String username) {
		return null;
	}

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserType(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUserEmail();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUserEmail(String username) {

		final User user = findByUserEmail(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}