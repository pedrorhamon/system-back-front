package com.starking.systemback.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.starking.systemback.model.enums.PerfilEnum;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author pedroRhamon
 */

@Entity
@Table(name = "usuario")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	@CPF
	private String cpf;
	
	private boolean isAtivo = true;
	
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfis;

}
