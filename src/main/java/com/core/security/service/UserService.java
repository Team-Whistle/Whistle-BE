package com.core.security.service;


import com.feature.user.dto.AuthenticatedUserDto;
import com.feature.user.dto.RegistrationResponse;
import com.feature.user.domain.User;
import com.feature.user.dto.RegistrationRequest;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
