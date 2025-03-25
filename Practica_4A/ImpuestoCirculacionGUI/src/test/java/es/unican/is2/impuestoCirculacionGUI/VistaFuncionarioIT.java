package es.unican.is2.impuestoCirculacionGUI;

import java.util.List;
import javax.swing.JTextField;

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
	
	@BeforeEach
	public void setUp() {
		GestionImpuestoCirculacion info = new GestionImpuestoCirculacion(new ContribuyentesDAO(), new VehiculosDAO());
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
		iut.textBox("txtDniContribuyente").enterText("11111111AAA");
		iut.button("btnBuscar").click();
		
		iut.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111AAA");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111BBB");
		
		iut.list("listMatriculasVehiculos").selectItems(0);
		iut.list("listMatriculasVehiculos").requireSelectedItems("1111BBB");
		
		iut.textBox("txtTotalContribuyente").requireText("206,75");

		
		//Caso no valido
		
		iut.textBox("txtDniContribuyente").enterText("12345678AAA");
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
