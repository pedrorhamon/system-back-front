package com.starking.systemback.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author pedroRhamon
 */

@AllArgsConstructor
@Data
public class TokenResponse {
	
	private String nome;
	private String token;
}
