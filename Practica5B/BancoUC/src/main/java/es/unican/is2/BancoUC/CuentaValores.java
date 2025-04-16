package es.unican.is2.BancoUC;


import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {		//CCogn: 1

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {	//CC: 1		CCog: 0
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() {			//CC: 1		CCog: 0
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
	
}
