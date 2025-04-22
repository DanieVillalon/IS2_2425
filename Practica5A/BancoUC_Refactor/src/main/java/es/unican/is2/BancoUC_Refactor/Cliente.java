package es.unican.is2.BancoUC_Refactor;


import java.util.LinkedList;
import java.util.List;

/**
 * Cálculo de métricas de complejidad:
 *  - WMC = Suma de las Complejidades ciclomáticas de todos los métodos de la clase. Los métodos abstractos no computan para el cálculo de la métrica
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Cliente {		//CCog: 3	CCogn: 0'33 (n = 9)
							//WMC: ∑CC = 11
							//CBO: 3 (Tarjeta, Cuenta y Direccion)
	
	public String nombre;
	public Direccion direccion;	//Refactorizado: extract class -> Direccion
	public String telefono;
	public String dni;
	
    private List<Cuenta> cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, Direccion direccion, String telefono, String dni) {  //Refactorizado: extract class -> Direccion
 																						//CC: 1		CCog: 0
		this.nombre = titular;
		this.direccion = direccion;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String cp, String localidad) {	//CC: 1		CCog: 0
		this.direccion = new Direccion(calle, cp, localidad);
	}
	
	public void anhadeCuenta(Cuenta c) { 	//CC: 1		CCog: 0
		cuentas.add(c);
	}
	
	
	public void anhadeTarjeta(Tarjeta t) {	//CC: 2		CCog: 2
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
	public double getSaldoTotal() {		//CC: 2		CCog: 1
		double total = 0.0;
		for (Cuenta c: cuentas) {  
			total += c.getSaldo();
		}
		return total;
	}

	
	public String getNombre() { 	//CC: 1		CCog: 0
		return nombre;
	}
	
	public Direccion getDireccion() { 	//CC: 1		CCog: 0
		return direccion;
	}

	public String getTelefono() { 	//CC: 1		CCog: 0
		return telefono;
	}

	public String getDni() { 	//CC: 1		CCog: 0
		return dni;
	}
	
	
}