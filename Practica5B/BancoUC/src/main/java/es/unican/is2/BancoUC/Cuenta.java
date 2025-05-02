package es.unican.is2.BancoUC;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Cuenta {	//CCog: 0 CCogn: 0	(n = 2)
						//WMC: ∑CC = 2
						//CBO: 1 (Cliente)
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {		//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return numCuenta;
	}
	
}
