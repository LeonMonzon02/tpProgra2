package billetera;

import java.util.List;

public abstract class Cuenta {

	private String cvu;
	private String alias;
	private double saldoInvertido;
	private double saldoDisponible;
	private List<Actividad> actividades;
	
}
