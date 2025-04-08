package es.unican.is2.BancoUC;


import java.util.LinkedList;
import java.util.List;

public class Cliente {		//CCogn: 0'8182
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  //CC: 1		CCog: 0
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) {	//CC: 1 CCog: 0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) {	//CC: 1	CCog: 0
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) {	//CC: 2	CCog: 2
		tarjetas.add(t);
		if (t instanceof Debito) {
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else {
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() {			//CC: 5	CCog: 7
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
	
	public String getNombre() {		//CC: 1 CCog: 0
		return nombre;
	}

	public String getCalle() {		//CC: 1	CCog: 0
		return calle;
	}

	public String getZip() {		//CC: 1	CCog: 0
		return zip;
	}

	public String getLocalidad() {	//CC: 1	CCog: 0
		return localidad;
	}

	public String getTelefono() {	//CC: 1 CCog: 0
		return telefono;
	}

	public String getDni() {		//CC: 1 CCog: 0
		return dni;
	}
	
	
	
}