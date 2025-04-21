package es.unican.is2.BancoUC;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = 1*8 + 2*3 + 3*2 = 20 (suma de las Complejidades ciclomáticas de todos los métodos de la clase)
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class CuentaAhorro extends Cuenta {		//CCog: 7	CCogn: 0'5385	(n = 13)

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException {	//CC: 1		CCog: 0	(sólo sentencias secuenciales)
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException {			//CC: 2		CCog: 1 (1 if)
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.Movimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	//CC: 3		CCog: 2 (2 if)
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.Movimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException {			//CC: 2		CCog: 1 (1 if)
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.Movimientos.add(m);
	}

	public void retirar(String concepto, double x) 
			throws saldoInsuficienteException, datoErroneoException {			//CC: 3		CCog: 2	(2 if)
		if (getSaldo() < x)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.Movimientos.add(m);
	}

	public double getSaldo() {		//CC: 2		CCog: 1	(1 for)
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {		//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {		//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() {			//CC: 1		CCog: 0	(sólo sencuencias secuenciales
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) {		//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() {		//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) {	//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() {				//CC: 1		CCog: 0	(sólo sencuencias secuenciales)
		return limiteDebito;
	}

}