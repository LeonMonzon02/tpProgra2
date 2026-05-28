package billetera;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public abstract class Cuenta {
	protected String cvu;
	protected String alias;
	protected double saldoInvertido;
	protected double saldoDisponible;
	protected List<Actividad> actividades;
	protected LocalDate fechaCreacion;
	protected Usuario titular;

	public Cuenta(String cvu, String alias, double saldo, LocalDate fechaCreacion, Usuario titular) {

    this.cvu = cvu;
    this.alias = alias;
    this.saldoDisponible = saldo;
    this.saldoInvertido = 0.0;
    this.fechaCreacion = fechaCreacion;
    this.titular = titular;
    this.actividades = new ArrayList<>();
	}
	public abstract void puedeInvertir(Inversion inversion);

	public void aumentarSaldoDisponible(double monto) {
		this.saldoDisponible += monto;
	}

	public void disminuirSaldoDisponible(double monto) {
		this.saldoDisponible -= monto;
	}

	public void aumentarSaldoInvertido(double monto) {
		this.saldoInvertido += monto;
	}

	public void disminuirSaldoInvertido(double monto) {
		this.saldoInvertido -= monto;
	}

	public void agregarActividad(Actividad actividad) {
		this.actividades.add(actividad);
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	

	public String getCvu() {
		return cvu;
	}
	
	public String getAlias() {
		return alias;	
	}
	
	public double getSaldoInvertido () {
		return saldoInvertido;
	}
	

	public void setCvu(String cvu) {
		this.cvu = cvu;
	}



	public void setAlias(String alias) {
		this.alias = alias;
	}



	public void setSaldoInvertido(double saldoInvertido) {
		this.saldoInvertido = saldoInvertido;
	}



	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	public Usuario getTitular() {
		return titular;
	}

	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}



	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	
}