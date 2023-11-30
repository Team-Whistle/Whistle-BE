package com.core.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Getter
@RequiredArgsConstructor
public class RegistrationException extends RuntimeException {

	private final String errorMessage;

}
