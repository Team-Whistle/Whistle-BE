package com.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiExceptionResponse {

	private String message;

	private HttpStatus status;

	private LocalDateTime time;

}
