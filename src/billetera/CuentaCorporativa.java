package billetera;
import java.time.LocalDate;

public class CuentaCorporativa extends Cuenta {
	private Empresa empresa;

    private String cuitEmpresa;

    public CuentaCorporativa(String cvu,
                             String alias,
                             double saldo,
                             LocalDate fechaCreacion,
                             String cuitEmpresa) {

        super(cvu, alias, saldo, fechaCreacion);

        this.cuitEmpresa = cuitEmpresa;
    }

	@Override
	public void puedeInvertir(Inversion inversion) {
		// TODO Esbozo de método generado automáticamente
		
	}
}
