package billetera;

public class RentaFija extends Inversion  {
	private double tasaInteres;

	public RentaFija(String cvu, String alias, double monto, String fechaApertura, double tasaInteres) {
		super(cvu, alias, monto, fechaApertura);
		this.tasaInteres = tasaInteres;
	}
	
	public double getTasaInteres() {
		return tasaInteres;
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
