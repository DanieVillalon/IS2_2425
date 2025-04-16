package es.unican.is2.BancoUC_Refactor;

public class Direccion {
	private String calle;
	private String cp;
	private String localidad;

	public Direccion(String calle, String cp, String localidad) {
		this.calle = calle;
		this.cp = cp;
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public String getCp() {
		return cp;
	}

	public String getLocalidad() {
		return localidad;
	}
}