package br.com.caelum.correios.soap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

public class CalcPrecoPrazoTest {

	@Test
	public void testCalculadoraPrecoPrazo() {
		
		CalcPrecoPrazoWSSoap calculadora = new CalcPrecoPrazoWS().getCalcPrecoPrazoWSSoap();
		assertNotNull(calculadora);
		
		// parâmetros
		String codigoSedex = "40010";
		String cepOrigemCaelumSP = "04101300"; //Caelum SP
		String cepDestino = "20040030"; // Caelum RJ
		String peso3kg = "3";
		BigDecimal comprimento20cm = new BigDecimal(20);
		BigDecimal altura10cm = new BigDecimal(10);
		BigDecimal largura15cm = new BigDecimal(15);
		BigDecimal diametro10cm = new BigDecimal(10);
		int formatoEncomendaCaixa = 1; // 1 é caixa ou pacote
		BigDecimal semValorDeclarado= BigDecimal.ZERO;
		String semEntregueEmMaos = "N";
		String semAvisoRecebimento = "N";
		String semCodigoEmpresa = "";
		String semSenhaEmpresa = "";

		//fazendo a chamada do serviço
		CResultado resultado = calculadora.calcPrecoPrazo(
		                semCodigoEmpresa, semSenhaEmpresa, 
		                codigoSedex, cepOrigemCaelumSP, cepDestino, 
		                peso3kg, formatoEncomendaCaixa, 
		                comprimento20cm, altura10cm, largura15cm, diametro10cm, 
		                semEntregueEmMaos, semValorDeclarado, semAvisoRecebimento);

		//recuperando o resultado
		List<CServico> servicosPesquisados = resultado.getServicos().getCServico();
		String valorFrete = servicosPesquisados.get(0).getValor();
		
		assertNotNull(valorFrete);
		assertFalse(valorFrete.isEmpty());
		assertTrue(Double.parseDouble(valorFrete.replace(",", ".")) > 0);
	}

}
