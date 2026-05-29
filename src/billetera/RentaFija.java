package billetera;

public class RentaFija extends Inversion {

	private static final double TASA_INTERES = 0.10;

	public RentaFija(int id, double monto, Cuenta cuentaOrigen, int plazo) {
		super(id, monto, cuentaOrigen, plazo, true);
	}
	
	public double getTasaInteres() {
		return TASA_INTERES;
	}
	
	@Override
	public double calcularGanancia() {
		return getMonto() * TASA_INTERES * getPlazo() / 365;
	}

	@Override
	public String descripcionOp() {
		StringBuilder sb = new StringBuilder();

		sb.append("FECHA: ").append(getFecha()).append(" - ");

		sb.append("ORIGEN: ")
		.append(getCuentaOrigen().getTitular().getDni())
		.append(" (")
		.append(getCuentaOrigen().getCvu())
		.append(") - ");

		sb.append("DESC: Inversion renta fija - ");

		sb.append("MONTO: $").append(getMonto()).append(" - ");

		sb.append("PLAZO: ").append(getPlazo()).append(" dias - ");

		if (estaAprobada()) {
			sb.append("ESTADO: Aprobada");
		} else {
			sb.append("ESTADO: Rechazada");
		}

		return sb.toString();
	}
}