package es.unican.is2.BancoUC_Refactor;


import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {
		super(numero, titular, cvc, cuentaAsociada);
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	private void procesarRetiro(String concepto, double x) {
		if (saldoDiarioDisponible<x) {
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar(concepto, x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {
		String concepto = "Retirada en cajero";
		procesarRetiro(concepto, x);
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {
		String concepto = "Compra en : " + datos;
		procesarRetiro(concepto, x);
	}
	
	public LocalDate getCaducidadDebito() {
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {
		return cuentaAsociada;
	}

}