package es.unican.is2.BancoUC_SonarQube;


import java.time.LocalDate;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. 
 *  		Los métodos abstractos no computan para el cálculo de la métrica.
 *  - WMCn = WMC/n con n el número de métodos de la clase.
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Debito extends Tarjeta {	//CCog: 1	CCogn: 0,14  (n = 7)
										//WMC: ∑CC = 8
										//CBO: 0

	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {	//CC: 1		CCog: 0
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	private void procesarRetiro(String concepto, double importe) {	//CC: 2		CCog: 1
		if (saldoDiarioDisponible<importe) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar(concepto, importe);
		saldoDiarioDisponible-=importe;
	}
	
	@Override
	public void retirar(double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 1		CCog: 0
		String concepto = "Retirada en cajero";
		procesarRetiro(concepto, importe);
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double importe) throws saldoInsuficienteException, datoErroneoException {	//CC: 1		CCog: 0
		String concepto = "Compra en : " + datos;
		procesarRetiro(concepto, importe);
	}
	
	public LocalDate getCaducidadDebito() {		//CC: 1		CCog: 0
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {		//CC: 1		CCog: 0
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {	//CC: 1		CCog: 0
		return cuentaAsociada;
	}

}