package com.starking.systemback.services;

/**
 * @author pedroRhamon
 */
public class ErroAutenticacao extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ErroAutenticacao(String msg) {
		super(msg);
	}
}
