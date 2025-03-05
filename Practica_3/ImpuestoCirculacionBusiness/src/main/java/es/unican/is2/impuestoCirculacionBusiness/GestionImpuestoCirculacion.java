package es.unican.is2.impuestoCirculacionBusiness;

import es.unican.is2.impuestoCirculacionCommon.Contribuyente;
import es.unican.is2.impuestoCirculacionCommon.DataAccessException;
import es.unican.is2.impuestoCirculacionCommon.IContribuyentesDAO;
import es.unican.is2.impuestoCirculacionCommon.IGestionContribuyentes;
import es.unican.is2.impuestoCirculacionCommon.IGestionVehiculos;
import es.unican.is2.impuestoCirculacionCommon.IInfoImpuestoCirculacion;
import es.unican.is2.impuestoCirculacionCommon.IVehiculosDAO;
import es.unican.is2.impuestoCirculacionCommon.OperacionNoValidaException;
import es.unican.is2.impuestoCirculacionCommon.Vehiculo;

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IGestionVehiculos, IGestionContribuyentes{
	IContribuyentesDAO daoDeContribuyentes;
	IVehiculosDAO daoDeVehiculos;

	public GestionImpuestoCirculacion(IContribuyentesDAO cDAO, IVehiculosDAO vDAO) {
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

	@Override
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}
}
