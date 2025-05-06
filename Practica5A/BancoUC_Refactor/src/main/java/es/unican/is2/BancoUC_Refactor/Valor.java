package es.unican.is2.BancoUC_Refactor;

import es.unican.is2.BancoUC_Refactor.Valor;

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 *
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. 
 *  		Los métodos abstractos no computan para el cálculo de la métrica.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Valor {	//CCog: 0	CCogn: 0	(n = 7)
						//WMC: ∑CC = 7
						//CBO: 1 (CuentaValores)
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {	//CC: 1		CCog: 0
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() {	//CC: 1		CCog: 0
		return numAcciones;
	}

	public void setNumValores(int numValores) {	//CC: 1		CCog: 0
		this.numAcciones = numValores;
	}

	public double getCotizacion() {	//CC: 1		CCog: 0
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) {	//CC: 1		CCog: 0
		this.cotizacion = cotizacion;
	}

	public String getEntidad() {	//CC: 1		CCog: 0
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) {	//CC: 1		CCog: 0
		if (obj == null) {
			System.out.println("The object to compare shouldn't be null");
			throw new NullPointerException();
		}
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}
}