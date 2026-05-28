package billetera;
import java.time.LocalDate;
public class CuentaRegular extends Cuenta{
	
	private double saldo_max;
	
	public CuentaRegular(String cvu,
            String alias,
            double saldo,
            LocalDate fechaCreacion) {

		super(cvu, alias, saldo, fechaCreacion);
}
	@Override
	public void puedeInvertir(Inversion inversion) {
		// TODO Esbozo de método generado automáticamente
		
	}
}
