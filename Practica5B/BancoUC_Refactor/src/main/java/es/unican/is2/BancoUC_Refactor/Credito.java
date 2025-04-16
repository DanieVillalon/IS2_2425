package es.unican.is2.BancoUC_Refactor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
	private static final double COMISION = 0.05;
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) {
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {
		String msgERR = "Credito insuficiente";
		importe += importe * COMISION; // Comision por operacion con tarjetas credito (Refactorizado como constante)
		String c = "Retirada en cajero";
		nuevoMovimiento(c, importe, msgERR);

	}

	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws saldoInsuficienteException, datoErroneoException {
		String msgERR = "Saldo insuficiente";
		String c = "Compra a credito en: " + datos;
		nuevoMovimiento(c, importe, msgERR);
	}

	/**
	 * Refactorizado: código común a pagoEnEstablecimiento() y retirar()
	 * @param concepto Concepto asociado al movimiento
	 * @param importe Importe retirado de la cuenta
	 */
	private void nuevoMovimiento(String concepto, double importe, String msgERR) {
		if (importe<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + importe > credito)
			throw new saldoInsuficienteException(msgERR);
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-importe);
		MovimientosMensuales.add(m);

	}
	
    private double getGastosAcumulados() {
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() {
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(-r);
	
		if (r != 0)
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() {
		return MovimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {
		return historicoMovimientos;
	}

}