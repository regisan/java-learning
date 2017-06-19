package br.com.caelum.estoque.ws;

import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;

@WebService
public class EstoqueWS {
	
	private static final Logger log = Logger.getLogger(EstoqueWS.class.getName());

	private ItemDao dao = new ItemDao();
	
	public List<Item> getItens() {
		log.info("Chamando metodo getItens()");
		return dao.todosItens();
	}

}
