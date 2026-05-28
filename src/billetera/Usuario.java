package billetera;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
	private String dni;
	private String nombre;
	private String telefono;
	private String correoElectronico;
	//private String contraseña;
	private List<Cuenta> cuentas;
	private double totalInvertido;
	
	public Usuario(String dni, String nombre, String telefono, String correoElectronico) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.totalInvertido = 0.0;
		this.cuentas = new ArrayList<>();
	}

	public void agregarCuenta(Cuenta cuenta) {
		this.cuentas.add(cuenta);
	}

	public String verCuentas() {

	}
	
	public boolean cuentaExiste(String cvu) {
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getCvu().equals(cvu)) {
				return true;
			}
		}
		return false;
	}



	public double getTotalInvertido() {
		return totalInvertido;
	}

	public String getDni() {
		return dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List <Cuenta> getCuentas(){
		return cuentas;
	}
	
	
	
}
