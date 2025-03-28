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
		iut.button("btnBuscar").requireText("Buscar");
		iut.textBox("txtDniContribuyente").enterText("1111AAA");
		iut.button("btnBuscar").click();
		iut.textBox("txtNombreContribuyente").requireText("Juan Perez Lopez");
		
		iut.textBox("txtDniContribuyente").enterText("1111AAA");
		iut.
		
		
		
		
		
		
		
		
	}

}
