package com.starking.systemback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.systemback.model.response.UsuarioResponse;
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
	
	@GetMapping
	public ResponseEntity<Page<UsuarioResponse>> buscarTodos(Pageable pageable) {
		return ResponseEntity.ok().body(this.usuarioService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>obterPorId( @PathVariable("id") Long id ) {
		return ResponseEntity.ok().body(this.usuarioService.obterPorId(id).get());
	}

}
