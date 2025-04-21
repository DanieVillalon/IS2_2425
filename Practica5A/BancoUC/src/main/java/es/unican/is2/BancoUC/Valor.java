package es.unican.is2.BancoUC;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = 1*7 = 7 (suma de las Complejidades ciclomáticas de todos los métodos de la clase)
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {	//CCog: 0	CCogn: 0	(n = 7)
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() {					//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return numAcciones;
	}

	public void setNumValores(int numValores) {		//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.numAcciones = numValores;
	}

	public double getCotizacion() {					//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.cotizacion = cotizacion;
	}

	public String getEntidad() {					//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) {				//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}