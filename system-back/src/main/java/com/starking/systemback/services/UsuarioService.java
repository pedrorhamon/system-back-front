package com.starking.systemback.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starking.systemback.RegraNegocioException;
import com.starking.systemback.model.Usuario;
import com.starking.systemback.model.response.UsuarioResponse;
import com.starking.systemback.repositories.UsuarioRepository;
import com.starking.systemback.utils.ConstantesUtils;

/**
 * @author pedroRhamon
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public UsuarioResponse salvarUsuario(Usuario usuario) throws RegraNegocioException {
		validarEmail(usuario.getEmail());
		usuario.setCpf(usuario.getCpf());
		usuario.setName(usuario.getName());
		usuario.setPerfis(usuario.getPerfis());
		criptografarSenha(usuario);
		return  new UsuarioResponse(usuarioRepository.save(usuario));
	}
	
	
	public Page<UsuarioResponse> findAll(Pageable pageable) {
		return usuarioRepository.findAll(pageable).map(UsuarioResponse::new);
	}
	
	public Optional<UsuarioResponse> obterPorId(Long id) {
		return this.usuarioRepository.findById(id).map(UsuarioResponse::new);
	}
	
	public UsuarioResponse atualizarUsuario(Long id, Usuario usuarioAtualizado) throws RegraNegocioException {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(ConstantesUtils.USUARIO_NAO_ENCONTRADO_ID));

        // Atualiza os dados do usuário existente com os novos dados do usuárioAtualizado
        usuarioExistente.setName(usuarioAtualizado.getName());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setCpf(usuarioAtualizado.getCpf());
        usuarioExistente.setPerfis(usuarioAtualizado.getPerfis());
        criptografarSenha(usuarioExistente); // Se necessário

        // Salva e retorna o usuário atualizado
        return new UsuarioResponse(usuarioRepository.save(usuarioExistente));
    }
	
	public Usuario autenticar(String email, String senha) throws ErroAutenticacao {
		Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);
		this.autenticacaoSenha(senha, usuario);
		return usuario.get();
	}
	
	private void autenticacaoSenha(String senha, Optional<Usuario> usuario) throws ErroAutenticacao {
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao(ConstantesUtils.USUARIO_NAO_ENCONTRADO);
		}
		boolean senhasBatem = encoder.matches(senha, usuario.get().getSenha());
		if(!senhasBatem) {
			throw new ErroAutenticacao(ConstantesUtils.SENHA_INVALIDA);
		}
	}
	
	private void validarEmail(String email) throws RegraNegocioException {
		boolean existe = this.usuarioRepository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException(ConstantesUtils.USUARIO_CADASTRADO);
		}
	}
	
	private void criptografarSenha(Usuario usuario) {
		String senha = usuario.getSenha();
		String senhaCripto = encoder.encode(senha);
		usuario.setSenha(senhaCripto);
	}
	
	@Transactional
	public void deletarUsuario(Long id) {
		this.usuarioRepository.deleteById(id);
	}
}
