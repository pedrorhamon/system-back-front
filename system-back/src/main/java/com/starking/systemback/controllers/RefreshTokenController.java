package com.starking.systemback.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.systemback.model.RefreshToken;
import com.starking.systemback.model.request.RefreshTokenRequest;
import com.starking.systemback.model.response.TokenResponse;
import com.starking.systemback.services.JwtService;
import com.starking.systemback.services.RefreshTokenService;

import lombok.AllArgsConstructor;

/**
 * @author pedroRhamon
 */

@RestController
@RequestMapping("/refresh")
@AllArgsConstructor
public class RefreshTokenController {

	private final JwtService jwtService;
	
	private final RefreshTokenService refreshTokenService;
	
	@PostMapping("/refreshToken")
	public TokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequestDTO){
	    return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
	            .map(refreshTokenService::verifyExpiration)
	            .map(RefreshToken::getUserInfo)
	            .map(userInfo -> {
	                String accessToken = jwtService.GenerateToken(userInfo.getUsername());
	                return JwtResponseDTO.builder()
	                        .accessToken(accessToken)
	                        .token(refreshTokenRequestDTO.getToken()).build();
	            }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
	}
}
