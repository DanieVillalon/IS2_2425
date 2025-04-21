package es.unican.is2.BancoUC;


import java.util.LinkedList;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = 1*2 + 3 = 5 (suma de las Complejidades ciclomáticas de todos los métodos de la clase)
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class CuentaValores extends Cuenta {		//CCog: 3	CCogn: 1	(n = 3)

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() {			//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) {	//CC: 3		CCog: 3	(1 if anidado en 1 for-each)
		for (Valor v:valores) {
			if (v.getEntidad().equals(valor.getEntidad()))
				return false;
		}
		valores.add(valor);
		return true;
	}
	
}
