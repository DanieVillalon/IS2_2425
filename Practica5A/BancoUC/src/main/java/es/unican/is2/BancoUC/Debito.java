package es.unican.is2.BancoUC;


import java.time.LocalDate;

public class Debito extends Tarjeta {	//CCogn: 0'333
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {	//CC: 1		CCog: 0
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	//CC: 2		CCog: 1
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) 
			throws saldoInsuficienteException, datoErroneoException {						//CC: 2		CCog: 1
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() {													//CC: 1		CCog: 0
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {															//CC: 1		CCog: 0
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {												//CC: 1		CCog: 0
		return cuentaAsociada;
	}

}