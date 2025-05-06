package es.unican.is2.BancoUC_SonarQube;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. 
 *  		Los métodos abstractos no computan para el cálculo de la métrica.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Credito extends Tarjeta {		//CCog: 5	CCogn: 0,5 (n = 10)
											//WMC: ∑CC = 15
											//CBO: 1 (Movimiento)

	
	private static final double COMISION = 0.05;
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) {	//CC: 1		CCog:0 
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
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 1		CCog:0 
		String msgERR = "Credito insuficiente";
		importe += importe * COMISION; // Comision por operacion con tarjetas credito (Refactorizado como constante)
		String c = "Retirada en cajero";
		nuevoMovimiento(c, importe, msgERR);

	}

	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 1		CCog:0 
		String msgERR = "Saldo insuficiente";
		String c = "Compra a credito en: " + datos;
		nuevoMovimiento(c, importe, msgERR);
	}

	/**
	 * Refactorizado: código común a pagoEnEstablecimiento() y retirar()
	 * @param concepto Concepto asociado al movimiento
	 * @param importe Importe retirado de la cuenta
	 */
	private void nuevoMovimiento(String concepto, double importe, String msgERR) {	//CC: 3		CCog: 2
		if (importe<0)
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + importe > credito)
			throw new saldoInsuficienteException(msgERR);
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-importe);
		movimientosMensuales.add(m);

	}
	
    private double getGastosAcumulados() {	//CC: 2		CCog: 1
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() {	//CC: 1		CCog: 0
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {	//CC: 3		CCog: 2
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidacion de operaciones tarjeta credito");
		double r = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) {
			Movimiento m = (Movimiento) movimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(-r);
	
		if (r != 0)
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() {		//CC: 1		CCog: 0
		return movimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() {	//CC: 1		CCog: 0
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() {		//CC: 1		CCog: 0
		return historicoMovimientos;
	}

}