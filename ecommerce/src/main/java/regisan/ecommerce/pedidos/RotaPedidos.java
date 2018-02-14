package regisan.ecommerce.pedidos;

import org.apache.camel.Exchange;
import org.apache.camel.component.http4.HttpMethods;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RotaPedidos extends SpringRouteBuilder {
	
	@Override
	public void configure() throws Exception {

		from("file:pedidos?delay=5s&noop=true")
		.setProperty("pedidoId", xpath("/pedido/id/text()"))
	    .setProperty("clienteId", xpath("/pedido/pagamento/email-titular/text()"))
		.split()
			.xpath("/pedido/itens/item")
		.filter()
			.xpath("/item/formato[text()='EBOOK']")
		.setProperty("ebookId", xpath("/item/livro/codigo/text()"))
		.log("${id}")
		.marshal()
			.xmljson()
		.log("${id} \n ${body}")
		.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
		.setHeader(Exchange.HTTP_QUERY, simple("clienteId=${property.clienteId}&pedidoId=${property.pedidoId}&ebookId=${property.ebookId}"))
//		.setHeader(Exchange.FILE_NAME, simple("${file:name.noext}-${header.CamelSplitIndex}.json"))
//		.to("file:saida");
		.to("http4://localhost:8080/webservices/ebook/item");
	}
}
