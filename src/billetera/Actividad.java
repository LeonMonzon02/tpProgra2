package billetera;
import java.time.LocalDate;
public abstract class Actividad {
	protected double monto;
	protected Cuenta cuentaOrigen;
	protected Cuenta cuentaDestino;
	protected LocalDate fecha;
	
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
