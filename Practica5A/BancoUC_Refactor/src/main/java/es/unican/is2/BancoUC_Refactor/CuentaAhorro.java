package es.unican.is2.BancoUC_Refactor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. Los métodos abstractos no computan para el cálculo de la métrica
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class CuentaAhorro extends Cuenta {		//CCog: 4	CCogn: 0,28	(n = 14)
												//WMC: ∑CC = 18
												//CBO: 2 (Tarjeta y Movimiento)

	private List<Movimiento> movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException {	//CC: 1		CCog: 0
		super(numCuenta);
		movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double importe) throws datoErroneoException {	//CC: 1		CCog: 0
		String concepto = "Ingreso en efectivo";
		ingresar(concepto, importe);
	}

	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 1		CCog: 0
		String concepto = "Retirada de efectivo";
		retirar(concepto, importe);
	}

	public void ingresar(String concepto, double importe) throws datoErroneoException {		//CC: 2		CCog: 1
		if (importe <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		movimiento(concepto, importe);
	}

	public void retirar(String concepto, double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 3 	CCog: 2
		if (getSaldo() < importe)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (importe <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		movimiento(concepto, -importe);
	}
	
	private void movimiento(String concepto, double importe) {	//CC: 1		CCog: 0
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(importe);
		this.movimientos.add(m);
	}

	@Override
	public double getSaldo() {	//CC: 2		CCog: 1
		double r = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) {
			Movimiento m = (Movimiento) movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {	//CC: 1		CCog: 0
		movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {	//CC: 1		CCog: 0
		return movimientos;
	}

	public LocalDate getCaducidadDebito() {	//CC: 1		CCog: 0
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) {		//CC: 1		CCog: 0
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() {	//CC: 1		CCog: 0
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) {	//CC: 1		CCog: 0
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() {	//CC: 1		CCog: 0
		return limiteDebito;
	}

}