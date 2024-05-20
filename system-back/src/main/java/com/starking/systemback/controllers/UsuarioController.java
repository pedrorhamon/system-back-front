package com.starking.systemback.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starking.systemback.exception.RegraNegocioException;
import com.starking.systemback.model.RefreshToken;
import com.starking.systemback.model.Usuario;
import com.starking.systemback.model.response.TokenResponse;
import com.starking.systemback.model.response.UsuarioResponse;
import com.starking.systemback.services.ErroAutenticacao;
import com.starking.systemback.services.JwtService;
import com.starking.systemback.services.RefreshTokenService;
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
	
	@Autowired
	private RefreshTokenService refreshTokenService;

	@PostMapping
	public ResponseEntity<?> salvarUsuario(@RequestBody @Valid Usuario usuario) throws RegraNegocioException {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.salvarUsuario(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@RequestParam("id") Long id, @RequestBody Usuario usuarioAtualizado) throws RegraNegocioException {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.usuarioService.atualizarUsuario(id, usuarioAtualizado));
	}

	@GetMapping
	public ResponseEntity<Page<UsuarioResponse>> buscarTodos(Pageable pageable) {
		return ResponseEntity.ok().body(this.usuarioService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obterPorId(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(this.usuarioService.obterPorId(id).get());
	}

	@PostMapping("/autenticar")
	public ResponseEntity<TokenResponse> autenticar(@RequestBody Usuario usuario) throws ErroAutenticacao {
		Usuario autenticar = this.usuarioService.autenticar(usuario.getEmail(), usuario.getSenha());
		if(autenticar.isAtivo()) {
			String token = jwtService.gerarToken(usuario);
			TokenResponse tokenResponse = new TokenResponse(usuario.getName(), token);
			RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(usuario.getEmail());
			return ResponseEntity.ok(TokenResponse.builder()
					.nome(this.jwtService.gerarToken(usuario))
					.token(tokenResponse.getToken())
					.build());
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) throws RegraNegocioException {
		this.usuarioService.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

}
