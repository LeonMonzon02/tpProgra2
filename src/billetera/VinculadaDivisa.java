package billetera;

public class VinculadaDivisa extends Inversion {

    private String divisa;
    private double tasa;

    public VinculadaDivisa(int id, double monto, Cuenta cuentaOrigen, int plazo, String divisa, double tasa) {
        super(id, monto, cuentaOrigen, plazo, true);

        if (divisa == null || divisa.isEmpty()) {
            throw new RuntimeException("La divisa no puede ser nula o vacía");
        }

        if (tasa <= 0) {
            throw new RuntimeException("La tasa debe ser positiva");
        }

        this.divisa = divisa;
        this.tasa = tasa;
    }



	public String getDivisa() {
        return divisa;
    }

    public double getTasa() {
        return tasa;
    }

    @Override
    public double calcularGanancia() {
        double cotizacionActual = Utilitarios.consultarCotizacion(divisa);
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

        sb.append("DESC: Inversion vinculada a divisa ")
        .append(divisa)
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