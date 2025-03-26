package es.unican.is2.impuestoCirculacionGUI;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.impuestoCirculacionBusiness.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacionDAOH2.ContribuyentesDAO;
import es.unican.is2.impuestoCirculacionDAOH2.VehiculosDAO;



/**
 * Pruebas de la interfaz de usuario destinada al funcionario.
 */
public class VistaFuncionarioIT {
	
	private FrameFixture iut;			//Interface Under Test
	
	//COmponentes capa DAO
	ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
	VehiculosDAO vehiculosDAO = new VehiculosDAO();
	
	@BeforeEach
	public void setUp() {
		//Componentes capa negocio
		GestionImpuestoCirculacion info = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);
		
		//Componentes capa presentacion
		VistaFuncionario gui = new VistaFuncionario(info);		//Creación de la interfaz
		
		iut = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@AfterEach
	public void tearDown() {
		iut.cleanUp();
	}
	
	@Test
	public void test() {
		//Comprobar el aspecto de la interfaz
		iut.button("btnBuscar").requireText("Buscar");
		
		
		//Comprobar Caso Válido
		//Introducimos un DNI
		iut.textBox("txtDniContribuyente").enterText("11111111A");
		// Pulsamos el boton BUscar
		iut.button("btnBuscar").click();
		// Comprobamos la salida
		iut.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111AAA");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111BBB");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111CCC");
		
		iut.textBox("txtTotalContribuyente").requireText("206,75");

		
		//Caso no valido
		iut.textBox("txtDniContribuyente").enterText("12345678A");
		iut.button("btnBuscar").click();
		iut.textBox("txtNombreContribuyente").requireText("DNI Incorrecto");
		iut.list("listMatriculasVehiculos").requireItemCount(0);
		iut.textBox("txtTotalContribuyente").requireText("0");


		
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
