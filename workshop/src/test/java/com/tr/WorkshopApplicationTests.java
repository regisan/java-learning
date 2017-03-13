package com.tr;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.tr.repository.FuncionarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkshopApplicationTests {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	ClassLoaderTemplateResolver resolver;
	TemplateEngine engine;
	
	@Before
	public void setUp() {
		resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode("XML");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setPrefix("templates/");
		resolver.setSuffix(".xml");
		
		engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
	}
	

	@Test
	public void testCreateXml() throws IOException {
		StringWriter writer = new StringWriter();
		
		Context context = new Context(new Locale("pt", "BR"));
		context.setVariable("funcionarios",  funcionarioRepository.findAll());
		
		engine.process("funcionario_template", context, writer);
		
		String xml = writer.toString();
		System.out.println(xml);
		
		assertTrue(xml.contains("<nome>André</nome>"));
		assertTrue(xml.contains("<email>joana.prado@tr.com</email>"));
		assertTrue(xml.contains("<nomeDep>Bianca Figueiredo</nomeDep>"));
	}
	
	@Test
	public void testCreateXmlFile() throws IOException {
		File f = new File("src/test/resources/xml/funcionarios_test.xml");
		FileWriter writer = new FileWriter(f);
		
		Context context = new Context(new Locale("pt", "BR"));
		context.setVariable("funcionarios",  funcionarioRepository.findAll());
		
		engine.process("funcionario_template", context, writer);
		writer.close();
	
		assertTrue(f.exists());
	}

}
