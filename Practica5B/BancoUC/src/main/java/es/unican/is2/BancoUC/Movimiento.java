package es.unican.is2.BancoUC;


import java.time.LocalDateTime;

public class Movimiento {					//CCogn: 0
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() {					//CC: 1		CCog: 0
		return importe;
	}

	public void setI(double newMImporte) {	//CC: 1		CCog: 0
		importe = newMImporte;
	}
	
	public String getC() {					//CC: 1		CCog: 0
		return concepto;
	}

	public void setC(String newMConcepto) {	//CC: 1		CCog: 0
		concepto = newMConcepto;
	}

	public LocalDateTime getF() {			//CC: 1		CCog: 0
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) {	//CC: 1		CCog: 0
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) {		//CC: 1
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}