package br.com.caelum.estoque.ws;

import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {
	
	private static final Logger log = Logger.getLogger(EstoqueWS.class.getName());

	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	@WebResult(name="itens")
	public ListaItens getItens() {
		
		log.info("Chamando metodo getItens()");
		return new ListaItens(dao.todosItens());
	}

}
