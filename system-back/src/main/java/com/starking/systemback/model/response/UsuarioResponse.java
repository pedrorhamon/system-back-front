package com.starking.systemback.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
public class UsuarioResponse {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private boolean isAtivo;
	private String perfil;

	public UsuarioResponse(Long id, String name, String email, String cpf, boolean isAtivo, String perfil) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.isAtivo = isAtivo;
		this.perfil = perfil;
	}
}
