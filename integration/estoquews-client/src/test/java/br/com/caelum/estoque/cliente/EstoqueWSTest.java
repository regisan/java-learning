package br.com.caelum.estoque.cliente;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

public class EstoqueWSTest {

	@Test
	public void testChamadaWebServiceUsandoClasseGerada() {
		EstoqueWS client = new EstoqueWS_Service().getEstoqueWSImplPort();
		
		Filtros filtros = new Filtros();
		filtros.getFiltro();
		
		ListaItens listaItens = client.todosOsItens(filtros);
		assertNotNull(listaItens);
	}
	
	@Test
	public void testChamadaWebServiceUsandoClasseService() throws MalformedURLException  {
		
		URL url = new URL("http://localhost:8080/estoquews-web/EstoqueWS/EstoqueWSImpl?wsdl");
		QName qname = new QName("http://ws.estoque.caelum.com.br/", "EstoqueWS");
		
		Service service = Service.create(url, qname);
		
		EstoqueWS client = service.getPort(EstoqueWS.class);
		
		Filtros filtros = new Filtros();
		filtros.getFiltro();
		
		ListaItens listaItens = client.todosOsItens(filtros);
		assertNotNull(listaItens);
		
	}

}
