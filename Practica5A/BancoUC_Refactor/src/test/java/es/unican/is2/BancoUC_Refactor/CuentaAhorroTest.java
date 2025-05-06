package es.unican.is2.BancoUC_Refactor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CuentaAhorroTest {
	private CuentaAhorro sut;
	private static Movimiento m1, m2, m3;
	
	@BeforeAll
	public static void inicializaAuxiliares() {
		m1 = new Movimiento();
		m1.setI(100);
		m1.setC("Concepto1");
		m1.setF(LocalDateTime.now());
		m2 = new Movimiento();
		m2.setI(200);
		m2.setC("Concepto2");
		m2.setF(LocalDateTime.now());
		m3 = new Movimiento();
		m3.setI(1500);
		m3.setC("Concepto3");
		m3.setF(LocalDateTime.now());
	}

	@BeforeEach
	public void inicializa() {
		sut = new CuentaAhorro("794311");
	}

	@Test
	public void testConstructor() {
		assertEquals(1000, sut.getLimiteDebito());
		assertEquals(0, sut.getMovimientos().size());
		assertEquals("794311", sut.getNumCuenta() );
		assertNull(sut.getCaducidadDebito());
		assertNull(sut.getCaducidadCredito());		
	}
	
	@Test
	public void testSetGetFechasCaducidad() {
		LocalDate today = LocalDate.now();
		sut.setCaducidadCredito(today);
		sut.setCaducidadDebito(today);
		assertEquals(today, sut.getCaducidadDebito());
		assertEquals(today, sut.getCaducidadCredito());		
	}
	
	@Test
	public void testGetSaldoYAddMovimiento() {
		assertTrue(sut.getSaldo()==0);	

		sut.addMovimiento(m1);
		assertEquals(100, sut.getSaldo());
		assertEquals(1, sut.getMovimientos().size());
		
		sut.addMovimiento(m2);
		sut.addMovimiento(m3);
		assertEquals(1800, sut.getSaldo());
		assertEquals(3, sut.getMovimientos().size());
	}
	
	@Test
	public void testRetirarSinConcepto() {
		
		try {
			sut.retirar(-10);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Debe lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar(50);
			assertEquals(50, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			assertEquals("Retirada de efectivo", sut.getMovimientos().get(1).getC());
		} catch (datoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar(100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) { }
	
	}
	
	@Test
	public void testIngresarSinConcepto () {
	
		try {
			sut.ingresar(-1);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		try {
			sut.ingresar(0.01);
			assertEquals(0.01, sut.getSaldo());
			assertEquals(1, sut.getMovimientos().size());
			assertEquals("Ingreso en efectivo",sut.getMovimientos().get(0).getC());
			
			sut.ingresar(100);
			assertEquals(100.01, sut.getSaldo());
			assertEquals(2, sut.getMovimientos().size());
			
		} catch (datoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	
	@Test
	public void testIngresarConConcepto () {
	
		// Test ingresar negativo
		try {
			sut.ingresar("Ingreso", -1);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		}

		// Test ingresar el limite
		try {
			sut.ingresar("Ingreso1", 0.01);
			assertEquals(0.01, sut.getSaldo());
			assertEquals(1, sut.getMovimientos().size());
			assertEquals("Ingreso1", sut.getMovimientos().get(0).getC());
			
			sut.ingresar("Ingreso2", 100);
			assertTrue(sut.getSaldo()==100.01);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals("Ingreso2", sut.getMovimientos().get(1).getC());
			
		} catch (datoErroneoException e) {
			fail("No debe lanzar la excepci�n");
		}
		
	}
	
	@Test
	public void testRetirarConConcepto() {
		
		try {
			sut.retirar("Retirada", -10);
			fail("Debe lanzar DatoErroneoException");
		} catch (datoErroneoException e) {
		} catch (saldoInsuficienteException e) {
			fail("Deber�a lanzar DatoErroneoException");
		}
		
		sut.addMovimiento(m1);
		
		try {
			sut.retirar("Retirada1", 50);
			assertTrue(sut.getSaldo()==50);
			assertTrue(sut.getMovimientos().size()==2);
			assertEquals("Retirada1", sut.getMovimientos().get(1).getC());
		} catch (datoErroneoException e) {
			fail("No debe lanzar DatoErroneoException");
		} catch (saldoInsuficienteException e) {
			fail("No debe lanzar SaldoInsuficienteException");
		}
		
		
		try {
			sut.retirar("Retirada2", 100);
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (datoErroneoException e) {
			fail("Debe lanzar SaldoInsuficienteException");
		} catch (saldoInsuficienteException e) {
			
		}
	
	}

	
}
