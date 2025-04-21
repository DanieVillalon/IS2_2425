package es.unican.is2.BancoUC;


import java.time.LocalDate;
/**
 * Cálculo de métricas de complejidad:
 *  - WMC = 1*4 + 2*2 = 8 (suma de las Complejidades ciclomáticas de todos los métodos de la clase)
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Debito extends Tarjeta {	//CCog: 2	CCogn: 0'333	(n = 6)
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	//CC: 2		CCog: 1 (1 if)
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException {						//CC: 2		CCog: 1 (1 if)
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() {													//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {															//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {												//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		return cuentaAsociada;
	}

}