package com.starking.systemback.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.starking.systemback.model.RefreshToken;
import com.starking.systemback.repositories.RefreshTokenRepository;
import com.starking.systemback.repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

/**
 * @author pedroRhamon
 */

@Service
@AllArgsConstructor
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	
	private final UsuarioRepository usuarioRepository;
	
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = RefreshToken.builder()
				.usuario(this.usuarioRepository.findByEmail(username).get())
				.token(UUID.randomUUID().toString())
				.expiryDate(Instant.now().plusMillis(600000)) // set expiry of refresh token to 10 minutes - you can configure it application.properties file 
				.build();
		return this.refreshTokenRepository.save(refreshToken);
	}
	
	public Optional<RefreshToken> findByToken(String token) {
		return this.refreshTokenRepository.findByToken(token);
	}
}
