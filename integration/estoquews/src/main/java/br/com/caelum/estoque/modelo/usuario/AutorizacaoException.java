package br.com.caelum.estoque.modelo.usuario;

import java.util.Date;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {

	private static final long serialVersionUID = 42889115267785820L;

	public AutorizacaoException(String message) {
		super(message);
	}

	public InfoFault getFaultInfo() {
		return new InfoFault("Token invalido", new Date());
	}
}
