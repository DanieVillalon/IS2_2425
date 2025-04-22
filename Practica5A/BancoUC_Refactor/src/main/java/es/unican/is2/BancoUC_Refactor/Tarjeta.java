package es.unican.is2.BancoUC_Refactor;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. Los métodos abstractos no computan para el cálculo de la métrica
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public abstract class Tarjeta {		//CCog: 0	CCogn: 0	(n = 1)
									//WMC: ∑CC = 1
									//CBO: 2 (Cliente y CuentaAhorro)

	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;

	public Tarjeta(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada) {	//CC: 1		CCog: 0 
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param importe Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double importe) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param importe Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double importe)
			throws saldoInsuficienteException, datoErroneoException;
	
}