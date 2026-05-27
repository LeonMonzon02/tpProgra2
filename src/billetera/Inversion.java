package billetera;

import java.time.LocalDate;

public abstract class Inversion extends Actividad {
	protected boolean esPrecancelable = false;
	protected boolean estaPrecancelada = false;
	protected int plazo;
	protected String id;
	
	
	public abstract double calcularGanancia();
	
	public double getMonto() {
		return monto;
	}
	
	public int getPlazo() {
		return plazo;
	}
	
	public String getId() {
		return id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
}
