package com.starking.systemback.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author pedroRhamon
 */
@AllArgsConstructor
@Data
public class UsuarioRequest {
	
	private String email;
	private String senha;
}
