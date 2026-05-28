package billetera;

public class FondoLiquidez extends Inversion  {
	private double montoMin= 20000000;

	public FondoLiquidez(int id, double monto, Cuenta cuentaOrigen, int plazo, boolean esPrecancelable) {
		super(id, monto, cuentaOrigen, plazo, esPrecancelable);
	}

	@Override
	public double calcularGanancia() {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public String descripcionOp() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}
	 
}
