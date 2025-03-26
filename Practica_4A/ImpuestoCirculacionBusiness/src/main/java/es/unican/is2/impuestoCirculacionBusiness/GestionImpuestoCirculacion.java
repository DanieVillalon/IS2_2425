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
	
	
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		if (daoDeContribuyentes.contribuyente(c.getDni()) != null) {
			return null;
		}
		return daoDeContribuyentes.creaContribuyente(c);
	}
	
	
	public Contribuyente bajaContribuyente(String dni) throws DataAccessException, OperacionNoValidaException {
		if (daoDeContribuyentes.contribuyente(dni) == null) {
			return null;
		}
		Contribuyente c = daoDeContribuyentes.contribuyente(dni);
		if (!c.getVehiculos().isEmpty()) {
			throw new OperacionNoValidaException("ERROR: El contribuyente con el DNI indicado, tiene vehiculos asociados a su nombre y no se puede dar de baja"); 
		}
		return daoDeContribuyentes.eliminaContribuyente(dni);
	}
	
	
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws DataAccessException, OperacionNoValidaException {
		if (daoDeContribuyentes.contribuyente(dni) == null) {
			return null;
		}
		if (daoDeVehiculos.vehiculoPorMatricula(v.getMatricula()) != null) {
			throw new OperacionNoValidaException("ERROR: Ya existe un vehiculo con esa matricula"); 
		}
		Contribuyente c = daoDeContribuyentes.contribuyente(dni);
		daoDeVehiculos.creaVehiculo(v);
		c.anhadeVehiculo(v);
		daoDeContribuyentes.actualizaContribuyente(c);
		return v;
	}
	
	public Vehiculo bajaVehiculo(String dni, String matricula) throws DataAccessException, OperacionNoValidaException {
		if (daoDeContribuyentes.contribuyente(dni) == null) {
			return null;
		}
		if (daoDeVehiculos.vehiculoPorMatricula(matricula) == null){
			return null;
		}
		Contribuyente c = daoDeContribuyentes.contribuyente(dni);
		if (c.buscaVehiculo(matricula) == null) {
			throw new OperacionNoValidaException("ERROR: El vehiculo no pertenece al contribuyente indicado");
		}
		Vehiculo v = daoDeVehiculos.vehiculoPorMatricula(matricula);
		c.eliminaVehiculo(v);
		daoDeVehiculos.eliminaVehiculo(matricula);
		daoDeContribuyentes.actualizaContribuyente(c);
		return v;
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
		
		cA.eliminaVehiculo(v);
		cN.anhadeVehiculo(v);	
		daoDeContribuyentes.actualizaContribuyente(cA);
		daoDeContribuyentes.actualizaContribuyente(cN);
		return true;
	} 
	
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		return daoDeContribuyentes.contribuyente(dni);
	}
	
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		return daoDeVehiculos.vehiculoPorMatricula(matricula);
	}
}
