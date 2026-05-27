package billetera;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Billetera implements IBilletera {
    // Tus variables de instancia (como las colecciones o mapas)
	Map<String, Usuario> usuarios;   // clave = DNI
	Map<String, Cuenta> cuentas;     // clave = CVU
	Map<String, Empresa> empresas;   // clave = CUIT
	Map<String, String> aliasToCvu;  // clave = alias
	List<String> historialGlobal;
    // Tu constructor


	public static void main(String[] args) {
		// TODO Esbozo de método generado automáticamente

	}

	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public String crearCuentaRegular(String dniUsuario, String alias) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public List<String> obtenerCuentas(String dniUsuario) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public double obtenerSaldoDisponible(String cvu) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		// TODO Esbozo de método generado automáticamente
		
	}

	@Override
	public String consultarCvu(String alias) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public List<String> consultarHistorialGlobal() {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

}
