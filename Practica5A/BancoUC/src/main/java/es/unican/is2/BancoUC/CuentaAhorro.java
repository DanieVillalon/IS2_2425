package es.unican.is2.BancoUC;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {		//CCogn: 0'583

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException {	//CC: 1		CCog: 0
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException {			//CC: 2		CCog: 1
		if (x <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.Movimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	//CC: 3		CCog: 2
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

	public void ingresar(String concepto, double x) throws datoErroneoException {			//CC: 2		CCog: 1
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
			throws saldoInsuficienteException, datoErroneoException {						//CC: 3		CCog: 2
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

	public double getSaldo() {																//CC: 2		CCog: 1
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {		//CC: 1		CCog: 0
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {		//CC: 1		CCog: 0
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() {			//CC: 1		CCog: 0
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) {		//CC: 1		CCog: 0
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() {		//CC: 1		CCog: 0
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) {	//CC: 1		CCog: 0
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() {				//CC: 1		CCog: 0
		return limiteDebito;
	}

}