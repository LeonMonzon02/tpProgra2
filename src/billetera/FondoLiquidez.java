package billetera;

public class FondoLiquidez extends Inversion {

    private static final double monto_minimo= 20000000;
    private static final double tasa = 0.08;
    private static final String activo = "FLE";

    public FondoLiquidez(int id, double monto, Cuenta cuentaOrigen, int plazo) {
        super(id, monto, cuentaOrigen, plazo, false);

        if (monto < monto_minimo) {
            throw new RuntimeException("El monto mínimo para Fondo de Liquidez es 20 millones");
        }
    }

    @Override
    public double calcularGanancia() {
        return getMonto() * tasa * getPlazo() / 365;
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

        sb.append("DESC: Fondo de liquidez empresarial ")
          .append(activo)
          .append(" - ");

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