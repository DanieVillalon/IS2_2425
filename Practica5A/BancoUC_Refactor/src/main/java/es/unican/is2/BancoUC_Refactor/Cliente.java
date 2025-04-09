package es.unican.is2.BancoUC_Refactor;


import java.util.LinkedList;
import java.util.List;

public class Cliente {
	
	public String nombre;
	public Direccion direccion;	//Refactorizado: extract class -> Direccion
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String cp, String localidad, 
 			String telefono, String dni) {  
		this.nombre = titular;
		this.direccion = new Direccion(calle, cp, localidad);
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String cp, String localidad) {
		this.direccion = new Direccion(calle, cp, localidad);
	}
	
	public void anhadeCuenta(Cuenta c) {
		Cuentas.add(c);
	}
	
	/**
	 * TODO: ¿Refactorizar?
	 */
	public void anhadeTarjeta(Tarjeta t) {
		tarjetas.add(t);
		if (t instanceof Debito) {
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	/**
	 * Refactorizado: funcionalidad extraída a la clase Cuenta
	 */
	public double getSaldoTotal() {
		double total = 0.0;
		for (Cuenta c: Cuentas) {  
			total = c.getSaldo();
		}
		return total;
	}

	
	public String getNombre() {
		return nombre;
	}

	public String getCalle() {
		return direccion.getCalle();
	}

	public String getZip() {
		return direccion.getCp();
	}

	public String getLocalidad() {
		return direccion.getLocalidad();
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDni() {
		return dni;
	}
	
	
}