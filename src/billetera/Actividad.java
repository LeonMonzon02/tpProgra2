package billetera;

public abstract class Actividad {

	//private fecha fecha
	private double monto;
	private Cuenta cuentaOrigen;
	
	
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
}
