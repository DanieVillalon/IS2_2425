

/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) {	//CC: 1
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual;
	}
	
	public int getNumValores() {							//CC: 1
		return numAcciones;
	}

	public void setNumValores(int numValores) {	//CC: 1
		this.numAcciones = numValores;
	}

	public double getCotizacion() {	//CC: 1
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) {	//CC: 1
		this.cotizacion = cotizacion;
	}

	public String getEntidad() {	//CC: 1
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) {	//CC: 1
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}