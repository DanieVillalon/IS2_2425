package es.unican.is2.impuestoCirculacionCommon;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

import es.unican.is2.impuestoCirculacionCommon.TipoMotor;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {
	
	private double potencia;
	
	/**
	 * Constructor de la clase turismo
	 * @param id, identificador unívoco del turismo
	 * @param matricula, matrícula del turismo
	 * @param fechaMatriculacion, fecha de matriculación del turismo
	 * @param motor, tipo de motor del turismo
	 * @param potencia, potencia total del turismo
	 */
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);

		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
		double precio = -1;
		
		if(this.potencia < 8) {
			precio = 25;			
		} else if (8 <= this.potencia && this.potencia < 12){		
			precio = 67;
		} else if (12 <= this.potencia && this.potencia < 16) {
			precio = 143;
		} else if (16 <= this.potencia && this.potencia < 20) {
			precio = 178;
		} else {
			precio = 223;
		}
		
		if(LocalDate.now().minusYears(25).isAfter(getFechaMatriculacion())) {
			precio = 0;
		} else {
			TipoMotor motor = this.getMotor();
			switch (motor) {
			case ELECTRICO:
				precio = precio * motor.getDescuentoImpuesto();
				break;
			case GAS:
				if (LocalDate.now().minusYears(1).isBefore(getFechaMatriculacion())) {
					precio = precio * motor.getDescuentoImpuesto();					
				}
				break;
			case HIBRIDO:
				if (LocalDate.now().minusYears(4).isBefore(getFechaMatriculacion())) {
					precio = precio * motor.getDescuentoImpuesto();					
				}
				break;
			default:
				break;
			}
		}
		
		return precio;
	}

}
