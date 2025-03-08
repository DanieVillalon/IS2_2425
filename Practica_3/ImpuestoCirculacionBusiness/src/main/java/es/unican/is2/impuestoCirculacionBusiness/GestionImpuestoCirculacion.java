package es.unican.is2.impuestoCirculacionBusiness;

import java.util.List;

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
	
	@Override
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) 
			throws OperacionNoValidaException, DataAccessException {
		Vehiculo v = daoDeVehiculos.vehiculoPorMatricula(matricula);
		if (v == null) {
			System.out.println("ERROR: El vehículo no se encuentra registrado");
			return false;
		}
		
		Contribuyente cA = daoDeContribuyentes.contribuyente(dniActual);
		if (cA == null) {
			System.out.println("ERROR: El contribuyente titular no se encuentra registrado");
			return false;
		}
		
		Contribuyente cN = daoDeContribuyentes.contribuyente(dniNuevo);
		if (cN == null) {
			System.out.println("ERROR: El contribuyente no titular no se encuentra registrado");
			return false;
		}
		
		if (cA.buscaVehiculo(matricula) == null) {
			throw new OperacionNoValidaException("ERROR: El dni indicado no pertenece al titular del vehículo");
		}
		
		cA.getVehiculos().remove(v);
		cN.getVehiculos().add(v);	//TODO:(ELIMINAR ANTES DE ENTREGAR) puede no funcionar con contribuyentes sin vehículos
		
		return true;
	} 
	
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		
		return daoDeContribuyentes.contribuyente(dni);
	}
	
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		return daoDeVehiculos.vehiculoPorMatricula(matricula);
	}
}
