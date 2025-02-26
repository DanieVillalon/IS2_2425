package es.unican.is2.impuestoCirculacionBusiness;

import es.unican.is2.impuestoCirculacionCommon.Contribuyente;
import es.unican.is2.impuestoCirculacionCommon.Vehiculo; 

public class GestionImpuestoCirculacion {

	public GestionImpuestoCirculacion() {
		
	}
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		return null;
	}
	
	public Contribuyente bajaContribuyente(String dni) {
		return null;
	}
	
	public Vehiculo altaVehiculo(Vehiculo v, String dni) {
		return null;
	}
	
	public Vehiculo bajaVehiculo(String dni, String matricula) {
		return null;
	} 
	
	public boolean cambiarTitularVehiculo(String matricula, String dniActual, String dniNuevo) {
		return true;
	} 
	
	public Contribuyente contribuyente(String dni) {
		return null;
	}
	
	public Vehiculo vehiculo(String matricula) {
		return null;
	}
}
