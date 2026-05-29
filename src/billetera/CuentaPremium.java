package billetera;
import java.time.LocalDate;

public class CuentaPremium extends Cuenta {
	private double saldo_min;

public CuentaPremium(String cvu, String alias, double saldo, LocalDate fechaCreacion, Usuario titular) {
    super(cvu, alias, saldo, fechaCreacion, titular);
    this.saldo_min = 500000.0;
}

	@Override
	public void puedeInvertir(Inversion inversion) {
		// TODO Esbozo de método generado automáticamente
		
	}
}
