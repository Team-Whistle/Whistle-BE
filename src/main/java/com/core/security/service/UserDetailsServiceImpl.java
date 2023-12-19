package com.core.security.service;

import com.core.exceptions.NotFoundException;
import com.feature.user.domain.User;
import com.feature.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetailsImpl loadUserByUsername(String username) {

		User user = userRepository.findByUserEmail(username);

		if (user == null) {
			throw new NotFoundException("사용자를 찾을수 없습니다.","05");
		}

		return new UserDetailsImpl(user);
	}
}
