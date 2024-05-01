package com.starking.systemback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.systemback.model.Usuario;

/**
 * @author pedroRhamon
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
