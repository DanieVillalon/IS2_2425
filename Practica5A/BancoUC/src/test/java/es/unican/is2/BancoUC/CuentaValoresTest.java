package es.unican.is2.BancoUC;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaValoresTest {
	
	private CuentaValores sut;
	
	@BeforeEach
	public void inicializa() {
		sut = new CuentaValores("794311");
	}
	
	@Test
	public void testConstructor() {
		assertTrue(sut.getNumCuenta().equals("794311"));
		assertTrue(sut.getValores().size()==0);
	}
	
	@Test
	public void testAnhadeValor() {
		// CASOS VALIDOS
		Valor v = new Valor("Telepizza", 25, 1.05);
		assertTrue(sut.anhadeValor(v));
		assertTrue(sut.getValores().size()==1);
		assertEquals(sut.getValores().get(0), v);
		
		v = new Valor("BancoSantander", 100, 200);
		assertTrue(sut.anhadeValor(v));
		assertTrue(sut.getValores().size()==2);
		assertEquals(sut.getValores().get(1), v);
		
		// CASOS NO VALIDOS
		assertFalse(sut.anhadeValor(new Valor("Telepizza", 10, 2.5)));
		
	}
}
