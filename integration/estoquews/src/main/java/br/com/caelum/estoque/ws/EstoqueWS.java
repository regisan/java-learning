package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class EstoqueWS {
	
	private static final Logger log = Logger.getLogger(EstoqueWS.class.getName());

	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	@ResponseWrapper(localName="itens")
	@WebResult(name="item")
	@RequestWrapper(localName="listaItens")
	public List<Item> getItens(@WebParam(name="filtros") Filtros filtros) {
		
		log.info("Chamando metodo getItens()");
		List<Filtro> listaDeFiltros = (filtros != null ? filtros.getLista() : new ArrayList<>());
		return dao.todosItens(listaDeFiltros);
	}

	
	@WebMethod(operationName="cadastrarItem")	
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true) TokenUsuario token,
			                  @WebParam(name="item") Item item)	throws AutorizacaoException {
		
		log.info("Cadastrando item " + item + ". Token: " + token);
		if (!new TokenDao().ehValido(token)) {
			throw new AutorizacaoException("Autorizacao falhou");
		}
		
		new ItemValidador(item).validate();
		 
		this.dao.cadastrar(item);
		return item;
	}
	
	@WebMethod(operationName="enviarNotificacao")
	@Oneway
	public void enviarNotificacao() {
		
	}
}
