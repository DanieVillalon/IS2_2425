package es.unican.is2.BancoUC_Refactor;


import java.util.LinkedList;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. 
 *  		Los métodos abstractos no computan para el cálculo de la métrica.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class CuentaValores extends Cuenta {		//CCog: 4	CCogn: 1  (n = 4)
												//WMC: ∑CC = 8
												//CBO: 1 (Valor)

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {	//CC: 1		CCog: 0
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() {	//CC: 1		CCog: 0
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) {	//CC: 3		CCog: 3
		for (Valor v:valores) {
			if (v.getEntidad().equals(valor.getEntidad()))
				return false;
		}
		valores.add(valor);
		return true;
	}

	@Override
	public double getSaldo() {	//CC: 2		CCog: 1
		double total = 0;
		for (Valor v: valores) {
			total += v.getCotizacion()*v.getNumValores();
		}
		return total;
	}
	
}
