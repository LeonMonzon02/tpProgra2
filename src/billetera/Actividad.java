package billetera;
import java.time.LocalDate;
public abstract class Actividad {
	protected double monto;
	protected Cuenta cuentaOrigen;
	protected Cuenta cuentaDestino;
	protected LocalDate fecha;
	
    public Actividad(double monto, Cuenta cuentaOrigen) {
        this.fecha = Utilitarios.hoy();
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
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
