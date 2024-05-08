package com.starking.systemback.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.starking.systemback.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author pedroRhamon
 */

@Service
public class JwtService {
	
	@Value("${jwt.chave-assinatura}")
	private String chaveAssinatura; 
	
	@Value("${jwt.expiracao}")
	private String expiracao; 
	
	public String gerarToken(Usuario usuario) {
		long exp = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(exp);
		Instant instant = dataHoraExpiracao.atZone( ZoneId.systemDefault() ).toInstant();
		Date data = Date.from(instant);
		
		String horaExpiracaoToken = dataHoraExpiracao.toLocalTime()
				.format(DateTimeFormatter.ofPattern("HH:mm"));
		
		String token = Jwts
							.builder()
							.setExpiration(data)
							.setSubject(usuario.getEmail())
							.claim("userid", usuario.getId())
							.claim("nome", usuario.getName())
							.claim("horaExpiracao", horaExpiracaoToken)
							.signWith( SignatureAlgorithm.HS512 , chaveAssinatura )
							.compact();
		
		return token;
	}
	
	public String obterLoginUsuario(String token) {
		Claims claims = obterClaims(token);
		return claims.getSubject();
	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean isTokenValido(String token) throws ExpiredJwtException {
		Claims claims = obterClaims(token);
		Date dataEx = claims.getExpiration();
		LocalDateTime dataExpiracao = dataEx.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		boolean dataHoraAtualIsAfterDataExpiracao = LocalDateTime.now().isAfter(dataExpiracao);
		return !dataHoraAtualIsAfterDataExpiracao;
	}
}
