package com.starking.systemback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.starking.systemback.model.Usuario;
import com.starking.systemback.repositories.UsuarioRepository;

/**
 * @author pedroRhamon
 */
public class SecurityUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuarioEncontrado = usuarioRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado."));
		return User.builder()
				.username(usuarioEncontrado.getEmail())
				.password(usuarioEncontrado.getSenha())
				.roles(usuarioEncontrado.getPerfis().name())
				.build();
	}

}
