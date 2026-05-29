package billetera;

public class Transferencia extends Actividad {

    private Cuenta cuentaDestino;


    public Transferencia(double monto, Cuenta cuentaOrigen, Cuenta cuentaDestino, boolean aprobada) {
        super(monto, cuentaOrigen, aprobada);

		this.cuentaDestino = cuentaDestino;
    }	

    @Override
    public String descripcionOp() {//StringBuilder
		StringBuilder sb = new StringBuilder();
		sb.append("FECHA: ").append(fecha).append(" - ");


		sb.append("ORIGEN: ")
		.append(getCuentaOrigen().getTitular().getDni())
		.append(" (")
		.append(getCuentaOrigen().getCvu())
		.append(") - ");

		sb.append("DESTINO: ")
		.append(getCuentaDestino().getTitular().getDni())
		.append(" (")
		.append(getCuentaDestino().getCvu())
		.append(") - ");

		sb.append("MONTO: $").append(getMonto()).append(" - ");

		if (estaAprobada()) {
			sb.append("ESTADO: Aprobada");
		} else {
			sb.append("ESTADO: Rechazada");
		}

		return sb.toString();
    }

    public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}


	public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }
}