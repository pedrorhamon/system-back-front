package com.starking.systemback;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starking.systemback.model.Usuario;
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
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody @Valid Usuario usuario) throws RegraNegocioException {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.salvarUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity<Page<UsuarioResponse>> buscarTodos(Pageable pageable) {
		return ResponseEntity.ok().body(this.usuarioService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>obterPorId( @PathVariable("id") Long id ) {
		return ResponseEntity.ok().body(this.usuarioService.obterPorId(id).get());
	}

}
