package billetera;

import java.util.List;
public abstract class Cuenta {
	protected String cvu;
	protected String alias;
	protected double saldoInvertido;
	protected double saldoDisponible;
	protected List<Actividad> actividades;
	
	
}
	
	public abstract void puedeInvertir(Inversion inversion);
	
	public String getCvu() {
		return cvu;
	}
	
	public String getAlias() {
		return alias;	
	}
	
	public double getSaldoInvertido () {
		return saldoInvertido;
	}
	
	public double getSaldoDispoinible() {
		return saldoDisponible;
	}
	
	public List<Actividad> getActividades() {
		return actividades;
	}
	
	
}