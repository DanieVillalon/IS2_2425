package es.unican.is2.BancoUC_Refactor;


import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) {
		super(numCuenta);
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() {
		return valores;
	}
	
	public boolean anhadeValor(Valor valor) {
		for (Valor v:valores) {
			if (v.getEntidad().equals(valor.getEntidad()))
				return false;
		}
		valores.add(valor);
		return true;
	}

	@Override
	public double getSaldo() {
		double total = 0;
		for (Valor v: valores) {
			total += v.getCotizacion()*v.getNumValores();
		}
		return total;
	}
	
}
