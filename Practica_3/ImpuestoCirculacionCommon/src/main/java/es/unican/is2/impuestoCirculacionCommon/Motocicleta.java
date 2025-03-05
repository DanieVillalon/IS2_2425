package es.unican.is2.impuestoCirculacionCommon;


import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		int tarifa;
		if (cilindrada <= 125) {
			tarifa = 8;
		} else if (125 < cilindrada && cilindrada <= 250) {
			tarifa = 15;
		} else if (250 < cilindrada && cilindrada <= 500) {
			tarifa = 30;
		} else if (500 < cilindrada && cilindrada <= 1000) {
			tarifa = 60;
		} else {
			tarifa = 120;
		}
		LocalDate actual = LocalDate.now();
		if (actual.isAfter(getFechaMatriculacion().plusYears(25))) {
			return 0;
		}
		switch (this.getMotor()) {
		case ELECTRICO: 
			return tarifa - (tarifa * 0.75);
		case HIBRIDO:
			
			if (actual.isBefore(this.getFechaMatriculacion().plusYears(4))) {
				return tarifa - (tarifa * 0.75);
			}
		case GAS:
			if (actual.isBefore(this.getFechaMatriculacion().plusYears(1))) {
				return tarifa - (tarifa * 0.5);
			}
		}
		
		return tarifa;
	}

}
