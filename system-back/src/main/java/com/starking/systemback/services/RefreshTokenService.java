package com.starking.systemback.services;

import org.springframework.stereotype.Service;

import com.starking.systemback.repositories.RefreshTokenRepository;

import lombok.AllArgsConstructor;

/**
 * @author pedroRhamon
 */

@Service
@AllArgsConstructor
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
}
