package br.com.caelum.estoque.modelo.item;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

public class TesteItemParaXml {

	@Test
	public void testItemParaXml() throws JAXBException {
		Item item = new Item.Builder()
				.comCodigo("MEA")
				.comNome("MEAN")
				.comQuantidade(5)
				.comTipo("Livro")
				.build();

		
		try (StringWriter writer = new StringWriter())  {
			
			JAXBContext context = JAXBContext.newInstance(Item.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(item, writer);
			String xml = writer.toString();
		
			assertTrue(xml.contains("<codigo>MEA</codigo>"));
			assertTrue(xml.contains("<nome>MEAN</nome>"));
			assertTrue(xml.contains("<tipo>Livro</tipo>"));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
