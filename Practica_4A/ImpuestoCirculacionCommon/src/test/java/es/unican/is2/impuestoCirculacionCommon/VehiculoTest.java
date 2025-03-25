package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import java.time.LocalDate;

public class VehiculoTest {
	
	private Motocicleta sut1;
	
	private Turismo sut2;
		
	
	
	@Test
	public void testConstructorMotocicleta() {
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 125);
		
		assertEquals(1, sut1.getId());
		assertEquals("ABC1234", sut1.getMatricula());
		assertEquals(LocalDate.now().minusYears(3), sut1.getFechaMatriculacion());
		assertEquals(TipoMotor.HIBRIDO, sut1.getMotor());
		assertEquals(125, sut1.getCilindrada());
		
		assertThrows(OperacionNoValidaException.class, new Motocicleta(-3, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 125));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, null, LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 125));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, "ABC1234", LocalDate.now().plusDays(6), TipoMotor.HIBRIDO, 125));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, "ABC1234", null, TipoMotor.HIBRIDO, 125));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(3), null, 125));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, -1));
		assertThrows(OperacionNoValidaException.class, new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 0));
		
	}
	
	@Test
	public void testConstructorTurismo() {
		sut2 = new Turismo(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 20);
		
		assertEquals(20, sut2.getPotencia());

		assertThrows(OperacionNoValidaException.class, new Turismo(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, -1));
		assertThrows(OperacionNoValidaException.class, new Turismo(1, "ABC1234", LocalDate.now().minusYears(3), TipoMotor.HIBRIDO, 0));
		
	}

	@Test
	public void testPrecioImpuestoMotocicleta() {
		// Casos Válidos
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusMonths(3), TipoMotor.GAS, 100);
		assertEquals(4, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(1), TipoMotor.GAS, 125);
		assertEquals(8, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.GAS, 200);
		assertEquals(15, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.HIBRIDO, 200);
		assertEquals(3.75, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(4), TipoMotor.HIBRIDO, 250);
		assertEquals(15, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(5), TipoMotor.HIBRIDO, 300);
		assertEquals(30, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.DIESEL, 500);
		assertEquals(30, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(25), TipoMotor.DIESEL, 750);
		assertEquals(60, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(26), TipoMotor.GASOLINA, 1000);
		assertEquals(0, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 1000);
		assertEquals(60, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.GASOLINA, 1200);
		assertEquals(120, sut1.precioImpuesto());
		
		sut1 = new Motocicleta(1, "ABC1234", LocalDate.now().minusYears(2), TipoMotor.ELECTRICO, 1200);
		assertEquals(30, sut1.precioImpuesto());
		
	}
}
