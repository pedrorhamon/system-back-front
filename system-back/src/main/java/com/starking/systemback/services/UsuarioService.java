package com.starking.systemback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starking.systemback.model.Usuario;
import com.starking.systemback.model.response.UsuarioResponse;
import com.starking.systemback.repositories.UsuarioRepository;

/**
 * @author pedroRhamon
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Page<UsuarioResponse> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable).map(UsuarioResponse::new);
	}
	
	private void criptografarSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		String senhaCripto = encoder.encode(senha);
		usuario.setSenha(senhaCripto);
	}
}
