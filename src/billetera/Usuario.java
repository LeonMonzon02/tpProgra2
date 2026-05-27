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
