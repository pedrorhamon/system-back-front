package com.starking.systemback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.systemback.model.RefreshToken;

/**
 * @author pedroRhamon
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

}
