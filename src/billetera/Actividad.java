package billetera;
import java.time.LocalDate;
public abstract class Actividad {
	protected double monto;
	protected Cuenta cuentaOrigen;
	protected Cuenta cuentaDestino;
	protected LocalDate fecha;
	protected String descripcion;
	

    public Actividad(LocalDate fecha,double monto,String descripcion) {
		
    	this.fecha = fecha;
    	this.monto = monto;
    	this.descripcion = descripcion;
}

	public abstract String descripcionOp();
	
	public double getMonto() {
		return monto;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public Cuenta getOrigen() {
		return cuentaOrigen;
	}
	
	public Cuenta getDestino() {
		return cuentaDestino;
	}
}
