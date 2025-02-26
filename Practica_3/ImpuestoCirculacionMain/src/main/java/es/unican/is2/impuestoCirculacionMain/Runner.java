package es.unican.is2.impuestoCirculacionMain;

import es.unican.is2.impuestoCirculacionBusiness.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacionDAOH2.VehiculosDAO;
import es.unican.is2.impuestoCirculacionGUI.VistaFuncionario;

/**
 * Clase principal que construye la aplicaci�n de tres capas y lanza su ejecuci�n
 */
public class Runner {

	public static void main(String[] args) {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();
		
		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		// Componentes casa presentacion
		VistaFuncionario vista = new VistaFuncionario(negocio);
		
		// Lanza ejecuci�n
		vista.setVisible(true);
	}

}
