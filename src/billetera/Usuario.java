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
}
