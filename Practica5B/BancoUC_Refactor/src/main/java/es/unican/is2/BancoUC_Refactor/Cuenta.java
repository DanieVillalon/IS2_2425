package es.unican.is2.BancoUC_Refactor;

//Refactorizado: cuenta abstracta
public abstract class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {
		return numCuenta;
	}

	public abstract double getSaldo();
}
