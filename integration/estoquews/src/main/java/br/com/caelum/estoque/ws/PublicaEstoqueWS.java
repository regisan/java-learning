package br.com.caelum.estoque.ws;

import java.util.logging.Logger;

import javax.xml.ws.Endpoint;

public class PublicaEstoqueWS {
	
	private static final Logger log = Logger.getLogger(PublicaEstoqueWS.class.getName());

	public static void main(String[] args) {
		EstoqueWS ws = new EstoqueWS();
		String address = "http://localhost:8080/estoquews";
		
		log.info("iniciando servidor");
		Endpoint.publish(address, ws);

		log.info("servidor iniciado");
	}

}
