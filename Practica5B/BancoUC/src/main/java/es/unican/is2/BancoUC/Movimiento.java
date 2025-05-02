package es.unican.is2.BancoUC;


import java.time.LocalDateTime;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Movimiento {		//CCog: 0	CCogn: 0	(n = 7)
								//WMC: ∑CC = 7
								//CBO: 2 (Credito y CuentaAhorro)
	
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return importe;
	}

	public void setI(double newMImporte) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		importe = newMImporte;
	}
	
	public String getC() {					//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return concepto;
	}

	public void setC(String newMConcepto) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		concepto = newMConcepto;
	}

	public LocalDateTime getF() {			//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) {		//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}