package br.com.caelum.estoque.ws;

import java.util.Arrays;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebService(endpointInterface="br.com.caelum.estoque.ws.EstoqueWS", serviceName="EstoqueWS")
@Stateless
public class EstoqueWSImpl implements EstoqueWS {
	
	@PersistenceContext(unitName="primary")
	EntityManager em;

	@Override
	public ListaItens todosOsItens(Filtros filtros) {
		ListaItens lista = new ListaItens();
		Query query = em.createQuery("select i from Item i");

		lista.item = query.getResultList();
		return lista;
	}


	@Override
	public CadastrarItemResponse cadastrarItem(CadastrarItem parameters, TokenUsuario tokenUsuario)
			throws AutorizacaoFault {
		
		em.persist(parameters.getItem());
		CadastrarItemResponse response = new CadastrarItemResponse();
		response.setItem(parameters.getItem());
		return response;
	}
}
