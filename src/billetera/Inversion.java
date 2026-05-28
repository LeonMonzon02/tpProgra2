package billetera;

import java.time.LocalDate;

public abstract class Inversion extends Actividad {
	protected boolean esPrecancelable = false;
	protected boolean estaPrecancelada = false;
	protected int plazo;
	protected int id;
	
    public Inversion(int id, double monto, Cuenta cuentaOrigen, int plazo, boolean esPrecancelable) {
        super(LocalDate.now(), monto, "");
        this.id = id;
        this.plazo = plazo;
        this.esPrecancelable = esPrecancelable;
        this.estaPrecancelada = false;
    }


    public abstract double calcularGanancia();


    
    public void cancelar() {
        if (!esPrecancelable) {
            throw new RuntimeException("La inversión no es precancelable");
        }

        if (estaPrecancelada) {
            throw new RuntimeException("La inversión ya fue precancelada");
        }

        this.estaPrecancelada = true;
    }

    public boolean esPrecancelable() {
        return esPrecancelable;
    }

    public boolean estaPrecancelada() {
        return estaPrecancelada;
    }

    public int getId() {
        return id;
    }

    public int getPlazo() {
        return plazo;
    }

    public LocalDate getFechaVencimiento() {
        return getFecha().plusDays(plazo);
    }
}