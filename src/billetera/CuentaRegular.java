package billetera;
import java.time.LocalDate;
public class CuentaRegular extends Cuenta{
	
	private double saldo_max;
	
public CuentaRegular(String cvu, String alias, double saldo, LocalDate fechaCreacion, Usuario titular, double saldo_max) {
    super(cvu, alias, saldo, fechaCreacion, titular);
    this.saldo_max = 5000000.0;
}
	@Override
	public void puedeInvertir(Inversion inversion) {
		// TODO Esbozo de método generado automáticamente
		
	}
}
