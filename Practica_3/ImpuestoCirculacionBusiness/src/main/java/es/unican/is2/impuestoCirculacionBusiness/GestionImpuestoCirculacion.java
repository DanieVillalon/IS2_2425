package es.unican.is2.impuestoCirculacionBusiness;

import es.unican.is2.impuestoCirculacionCommon.Contribuyente;
import es.unican.is2.impuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.impuestoCirculacionCommon.Vehiculo;
import es.unican.is2.impuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacionDAOH2.VehiculosDAO; 

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion{
	ContribuyentesDAO daoDeContribuyentes;
	VehiculosDAO daoDeVehiculos;

	public GestionImpuestoCirculacion(ContribuyentesDAO cDAO, VehiculosDAO vDAO) {
		this.daoDeContribuyentes = cDAO;
		this.daoDeVehiculos = vDAO;
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
