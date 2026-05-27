package billetera;
import java.util.List;
public class Usuario {
	private String dni;
	private String nombre;
	private int telefono;
	private String correoElectronico;
	private Hash contraseña;
	private List<Cuenta> cuentas;
	private double totalInvertido;
	
	public Usuario(String dni, String nombre, int telefono, String correoElectronico, Hash contraseña) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.contraseña = contraseña;
		this.totalInvertido = 0.0;
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
