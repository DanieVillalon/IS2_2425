package es.unican.is2.BancoUC_Refactor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException {
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double importe) throws datoErroneoException {
		String concepto = "Ingreso en efectivo";
		ingresar(concepto, importe);
	}

	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		String concepto = "Retirada de efectivo";
		retirar(concepto, importe);
	}

	public void ingresar(String concepto, double importe) throws datoErroneoException {
		if (importe <= 0)
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		movimiento(concepto, importe);
	}

	public void retirar(String concepto, double importe) throws saldoInsuficienteException, datoErroneoException {
		if (getSaldo() < importe)
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (importe <= 0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		movimiento(concepto, -importe);
	}
	
	private void movimiento(String concepto, double importe) {
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(importe);
		this.Movimientos.add(m);
	}

	@Override
	public double getSaldo() {
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) {
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() {
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() {
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) {
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() {
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) {
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() {
		return limiteDebito;
	}

}