

import java.time.LocalDateTime;

public class Movimiento {					//CC: 1
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() {					//CC: 1
		return importe;
	}

	public void setI(double newMImporte) {	//CC: 1
		importe = newMImporte;
	}
	
	public String getC() {					//CC: 1
		return concepto;
	}

	public void setC(String newMConcepto) {	//CC: 1
		concepto = newMConcepto;
	}

	public LocalDateTime getF() {	//CC: 1
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) {	//CC: 1
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) {		//CC: 1
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe);
	}
	
}