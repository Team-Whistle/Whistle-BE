package com.core.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.feature.user.domain.User;
import com.feature.user.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenManager {

	private final JwtProperties jwtProperties;

	public String generateToken(User user) {

		final String username = user.getUserEmail();
		final UserRole userType = user.getUserType();

		//@formatter:off
		return JWT.create()
				.withSubject(username)
				.withIssuer(jwtProperties.getIssuer())
				.withClaim("userType", userType.name())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
				.sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
		//@formatter:on
	}

	public String getUsernameFromToken(String token) {
		try {
			final DecodedJWT decodedJWT = getDecodedJWT(token);
			String payload = decodedJWT.getPayload();
			String decodedPayload = new String(Base64.getDecoder().decode(payload));
			JSONObject payloadJson = new JSONObject(decodedPayload);
			String subject = payloadJson.getString("sub");
			return subject;
		} catch (Exception e) {
			log.error("Error decoding JWT token: {}", e.getMessage());
			throw new RuntimeException("Error decoding JWT token", e);
		}
	}

	public boolean validateToken(String token, String authenticatedUsername) {

		final String usernameFromToken = getUsernameFromToken(token);

		final boolean equalsUsername = usernameFromToken.equals(authenticatedUsername);
		final boolean tokenExpired = isTokenExpired(token);

		return equalsUsername && !tokenExpired;
	}

	private boolean isTokenExpired(String token) {

		final Date expirationDateFromToken = getExpirationDateFromToken(token);
		return expirationDateFromToken.before(new Date());
	}

	private Date getExpirationDateFromToken(String token) {

		final DecodedJWT decodedJWT = getDecodedJWT(token);

		return decodedJWT.getExpiresAt();
	}

	private DecodedJWT getDecodedJWT(String token) {

		final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();

		return jwtVerifier.verify(token);
	}

}
