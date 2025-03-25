package es.unican.is2.impuestoCirculacionCommon;

import java.time.LocalDate;

/**
 * Clase abstracta que representa un vehiculo. 
 * Cada vehiculo tiene una matricula unica.
 */
public abstract class Vehiculo {

	// Clave primaria autogenerada
	private long id;

	private String matricula;
	private LocalDate fechaMatriculacion;
	private TipoMotor motor;

	/**
	 * Metodo constructor de la clase Vehiculo
	 * @param id, identificador del vehiculo
	 * @param matricula, matricula del vehiculo
	 * @param fechaMatriculacion, fecha de su primera matriculacion
	 * @param motor, tipo motor
	 * @throws OperacionNoValidaException, si algun dato es incorrecto
	 */
	public Vehiculo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor) throws OperacionNoValidaException {
		if (id < 0 || matricula == null || fechaMatriculacion.isAfter(LocalDate.now()) || fechaMatriculacion == null || motor == null) {
			throw new OperacionNoValidaException("ERROR: datos introducidos no válidos");
		}
		
		this.id = id;
		this.matricula = matricula;
		this.fechaMatriculacion = fechaMatriculacion;
		this.motor = motor;
	}

	/**
	 * Retorna la matricula del vehiculo.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de primera matriculacion del vehiculo.
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}

	/**
	 * Retorna el tipo de motor del vehiculo.
	 */
	public TipoMotor getMotor() {
		return motor;
	}

	/**
	 * Retorna el identificador del vehiculo.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Calcula el impuesto de circulacion del vehiculo, segun caracteristicas y con bonificaciones aplicacadas
	 * @return el precio final del impuesto asignado al tipo de vehiculo
	 */
	public abstract double precioImpuesto();

}
