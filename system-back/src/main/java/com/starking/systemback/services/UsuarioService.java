package com.starking.systemback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starking.systemback.repositories.UsuarioRepository;

/**
 * @author pedroRhamon
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

}
