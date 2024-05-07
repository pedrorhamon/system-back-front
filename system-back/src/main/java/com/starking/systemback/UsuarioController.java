package com.starking.systemback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.systemback.services.JwtService;
import com.starking.systemback.services.UsuarioService;

/**
 * @author pedroRhamon
 */

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>obterPorId( @PathVariable("id") Long id ) {
		return ResponseEntity.ok().body(this.usuarioService.obterPorId(id).get());
	}

}
