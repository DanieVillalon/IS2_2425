package es.unican.is2.BancoUC;


public abstract class Tarjeta {	//CCogn: 0
	
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
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;
	
}