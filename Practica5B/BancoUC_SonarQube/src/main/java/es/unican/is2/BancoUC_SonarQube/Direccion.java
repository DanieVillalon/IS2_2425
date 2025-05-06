package es.unican.is2.BancoUC_SonarQube;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. 
 *  		Los métodos abstractos no computan para el cálculo de la métrica.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Direccion {	//CCog: 0	CCogn: 0 (n = 4)
							//WMC: ∑CC = 4
							//CBO: 1 (Cliente)
	
	private String calle;
	private String cp;
	private String localidad;

	public Direccion(String calle, String cp, String localidad) {	//CC: 1		CCog: 0
		this.calle = calle;
		this.cp = cp;
		this.localidad = localidad;
	}

	public String getCalle() {	//CC: 1		CCog: 0
		return calle;
	}

	public String getCp() {	//CC: 1		CCog: 0
		return cp;
	}

	public String getLocalidad() {	//CC: 1		CCog: 0
		return localidad;
	}
}