package es.unican.is2.BancoUC;


import java.util.LinkedList;
import java.util.List;


/**
 * Cálculo de métricas de complejidad:
 *  - WMC = 1*9 + 2 + 5 = 16 (suma de las Complejidades ciclomáticas de todos los métodos de la clase)
 *  - WMCn = WMC/n (Con n el número de métodos de la clase)
 *  - CCogn = CCog/n (contribuciones al CCog anotadas en cada método)
 */
public class Cliente {		//CCog: 9	CCogn: 0'8182 (n = 11)
							//WMC: ∑CC = 16
							//CBO: 2 (Tarjeta y Cuenta)
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  //CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) {	//CC: 1		CCog: 0 (sólo sentencias secuenciales)
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) {	//CC: 1	CCog: 0	(sólo sentencias secuenciales)
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) {	//CC: 2	CCog: 2 (1 if y 1 else)
		tarjetas.add(t);
		if (t instanceof Debito) {
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() {			//CC: 5	CCog: 7 (1 for con un if-elseif anidado (+2 +1) con un for anidado en el elseif (+3))
		double total = 0.0;
		for (Cuenta c: Cuentas) {  
			if (c instanceof CuentaAhorro) {
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  {
				for (Valor v: ((CuentaValores) c).getValores()) {
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() {		//CC: 1 CCog: 0	(sólo sentencias secuenciales)
		return nombre;
	}

	public String getCalle() {		//CC: 1	CCog: 0 (sólo sentencias secuenciales)
		return calle;
	}

	public String getZip() {		//CC: 1	CCog: 0 (sólo sentencias secuenciales)
		return zip;
	}

	public String getLocalidad() {	//CC: 1	CCog: 0 (sólo sentencias secuenciales)
		return localidad;
	}

	public String getTelefono() {	//CC: 1 CCog: 0 (sólo sentencias secuenciales)
		return telefono;
	}

	public String getDni() {		//CC: 1 CCog: 0 (sólo sentencias secuenciales)
		return dni;
	}
}