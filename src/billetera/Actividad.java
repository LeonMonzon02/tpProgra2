package billetera;
import java.time.LocalDate;
public abstract class Actividad {
	protected LocalDate fecha;
	protected double monto;
	protected Cuenta cuentaOrigen;
	protected boolean aprobada;
	

	public Actividad(double monto, Cuenta cuentaOrigen, boolean aprobada) {
		this.fecha = Utilitarios.hoy();
		this.monto = monto;
		this.cuentaOrigen = cuentaOrigen;
		this.aprobada = aprobada;
	}

	public abstract String descripcionOp();
	
    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public boolean estaAprobada() {
        return aprobada;
    }
}