package billetera;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Billetera implements IBilletera {
   
	Map<String, Usuario> usuarios;   // clave = DNI
	Map<String, Cuenta> cuentas;     // clave = CVU
	Map<String, Empresa> empresas;   // clave = CUIT
	Map<String, String> aliasToCvu;  // clave = alias
	List<Actividad> historialGlobal; // clave = id inversión
	Map<Integer, Inversion> inversiones;
	int proximoIdInversion;
	
	public Billetera() {
		this.usuarios = new HashMap<>();
		this.empresas = new HashMap<>(); 
		this.cuentas = new HashMap<>();
		this.aliasToCvu = new HashMap<>();
		this.inversiones = new HashMap<>();
		this.historialGlobal = new ArrayList<>();
		this.proximoIdInversion = 1;
	}
	
	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {
		if (this.empresas.containsKey(cuit)) { //comprueba si la empresa está registrada usando el cuit
			
			throw new IllegalArgumentException("la empresa ya se encuentra registrada");
		}
		
		Empresa nuevaEmpresa = new Empresa(cuit, nombreFantasia, email, telefono, nombreContacto); //registramos la empresa
		
		this.empresas.put(cuit, nuevaEmpresa);
		
	}

	@Override
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {

	    if (!empresas.containsKey(cuitEmpresa)) {
	        throw new IllegalArgumentException("la empresa no existe");
	    }

	    if (!usuarioExiste(dniAutorizado)) {
	        throw new IllegalArgumentException("el usuario no existe");
	    }

	    Empresa empresa = empresas.get(cuitEmpresa);
	    empresa.agregarAutorizado(dniAutorizado);
	}
	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {
        if (usuarioExiste(dni)) { //Comprobamos si el dni está dentro del MAP
            throw new IllegalArgumentException("El usuario ya se encuentra registrado"); //si el dni está en el MAP capturamos el error
        }

     
        Usuario nuevoUsuario = new Usuario(dni, nombre, telefono, email); //si no existe creamos el nuevo usuario

        
        this.usuarios.put(dni, nuevoUsuario); // lo guardamos en el MAP 
    }
	//metodos auxiliares
	
	private int generarIdInversion() {
		return proximoIdInversion++;
	}

	private boolean usuarioExiste(String dni) {
	    return usuarios.containsKey(dni);
	}
	

	
	private Usuario buscarUsuario (String dniUsuario) {
		Usuario usuario = usuarios.get(dniUsuario);
	    if (usuario == null) {
	        throw new IllegalArgumentException("El usuario no existe");
	    }

	    return usuario;
	
	}

	@Override
	public String crearCuentaRegular(String dniUsuario, String alias) {
		if (dniUsuario == null || dniUsuario.isEmpty()) {
			throw new RuntimeException("El DNI no puede ser nulo o vacío");
		}

		if (alias == null || alias.isEmpty()) {
			throw new RuntimeException("El alias no puede ser nulo o vacío");
		}

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}

		if (aliasToCvu.containsKey(alias)) {
			throw new RuntimeException("El alias ya está registrado");
		}

		String cvu = Utilitarios.generarSiguienteCvu();

		CuentaRegular cuenta = new CuentaRegular(cvu,alias,0.0,Utilitarios.hoy(),usuario);

		cuentas.put(cvu, cuenta);
		aliasToCvu.put(alias, cvu);
		usuario.agregarCuenta(cuenta);

		return cvu;
	}

	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
		if (dniUsuario == null || dniUsuario.isEmpty()) {
			throw new RuntimeException("El DNI no puede ser nulo o vacío");
		}

		if (alias == null || alias.isEmpty()) {
			throw new RuntimeException("El alias no puede ser nulo o vacío");
		}

		if (depositoInicial <= 0) {
			throw new RuntimeException("El depósito inicial debe ser positivo");
		}

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}

		if (aliasToCvu.containsKey(alias)) {
			throw new RuntimeException("El alias ya está registrado");
		}

		String cvu = Utilitarios.generarSiguienteCvu();

		CuentaPremium cuenta = new CuentaPremium(cvu,alias,depositoInicial,Utilitarios.hoy(),usuario);

		cuentas.put(cvu, cuenta);
		aliasToCvu.put(alias, cvu);
		usuario.agregarCuenta(cuenta);

		return cvu;
}

@Override
public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
    if (dniUsuario == null || dniUsuario.isEmpty()) {
        throw new RuntimeException("El DNI no puede ser nulo o vacío");
    }

    if (alias == null || alias.isEmpty()) {
        throw new RuntimeException("El alias no puede ser nulo o vacío");
    }

    if (cuitEmpresa == null || cuitEmpresa.isEmpty()) {
        throw new RuntimeException("El CUIT no puede ser nulo o vacío");
    }

    Usuario usuario = usuarios.get(dniUsuario);

    if (usuario == null) {
        throw new RuntimeException("El usuario no existe");
    }

    Empresa empresa = empresas.get(cuitEmpresa);

    if (empresa == null) {
        throw new RuntimeException("La empresa no existe");
    }

    if (!empresa.estaAutorizado(dniUsuario)) {
        throw new RuntimeException("El usuario no está autorizado para operar en nombre de la empresa");
    }

    if (aliasToCvu.containsKey(alias)) {
        throw new RuntimeException("El alias ya está registrado");
    }

    String cvu = Utilitarios.generarSiguienteCvu();

    CuentaCorporativa cuenta = new CuentaCorporativa(cvu,alias,0.0,Utilitarios.hoy(),usuario,empresa,cuitEmpresa);

    cuentas.put(cvu, cuenta);
    aliasToCvu.put(alias, cvu);
    usuario.agregarCuenta(cuenta);

    return cvu;
}
	@Override
	public List<String> obtenerCuentas(String dniUsuario) {

		    if (!usuarioExiste(dniUsuario)) {
		        throw new IllegalArgumentException("El usuario no existe");
		    }

		    Usuario usuario = usuarios.get(dniUsuario);

		    List<String> cuentasUsuario = new ArrayList<>();

		    for (Cuenta cuenta : usuario.getCuentas()) {

		        cuentasUsuario.add(cuenta.getCvu());

		    }

		    return cuentasUsuario;
		}
	
	@Override
	public double obtenerSaldoDisponible(String cvu) {
	    if (!cuentas.containsKey(cvu)) {
	        throw new IllegalArgumentException("La cuenta no existe");
	    }

	    Cuenta cuenta = cuentas.get(cvu);

	    return cuenta.getSaldoDisponible();
	}
	

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		if (cvuOrigen == null) {
			throw new RuntimeException("El CVU de origen no puede ser nulo");
		}
		if (cvuDestino == null) {
			throw new RuntimeException("El CVU de destino no puede ser nulo");
		}
		if (cvuOrigen.equals(cvuDestino)) {
			throw new RuntimeException("El CVU de origen y destino no pueden ser el mismo");
		}
		if (monto <= 0) {
			throw new RuntimeException("El monto debe ser positivo");
		}


		Cuenta cuentaOrigen = cuentas.get(cvuOrigen);
		Cuenta cuentaDestino = cuentas.get(cvuDestino); //busco en el map con la clave que le paso

		if (cuentaOrigen == null) {
			throw new RuntimeException("La cuenta de origen no existe");
		}
		if (cuentaDestino == null) {
			throw new RuntimeException("La cuenta de destino no existe");
		}

    	Transferencia transferencia; //se la paso antes sino rompe por el boolean de dsp

		if (cuentaOrigen.getSaldoDisponible() >= monto) {
			cuentaOrigen.disminuirSaldoDisponible(monto);
			cuentaDestino.aumentarSaldoDisponible(monto);

	        transferencia = new Transferencia(monto, cuentaOrigen, cuentaDestino, true);
		}	

		else {
        transferencia = new Transferencia(monto, cuentaOrigen, cuentaDestino, false);
		}

		historialGlobal.add(transferencia);
		cuentaOrigen.agregarActividad(transferencia);
		cuentaDestino.agregarActividad(transferencia);
	}

	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		if (dni.length() != 8 || dni.length() != 7)  {
			throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos");
		}
		if (cvu == null || cvu.isEmpty()) {
			throw new IllegalArgumentException("El CVU no puede ser nulo o vacío");
		}
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto debe ser positivo");
		}
		if (plazoDias <= 0) {
			throw new IllegalArgumentException("El plazo en días debe ser positivo");
		}

		
		for (Usuario usuario : usuarios.values()) {
			for (Cuenta cuenta : usuario.getCuentas() ) {
				if (cuenta.getCvu().equals(cvu)) {
					if (cuenta.getSaldoDisponible() < monto) {
						throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
					}
					cuenta.disminuirSaldoDisponible(monto);
					RentaFija nuevaInversion = new RentaFija(monto, plazoDias);
					cuenta.agregarActividad(nuevaInversion);
				}
			}
		}

	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa, double tasa) {
		if (dni == null || !(dni.length() == 7 || dni.length() == 8)) {
			throw new RuntimeException("El DNI debe tener 7 u 8 dígitos");
		}
		if (cvu == null || cvu.isEmpty()) {
			throw new RuntimeException("El CVU no puede ser nulo o vacío");
		}
		if (monto <= 0) {
			throw new RuntimeException("El monto debe ser positivo");
		}
		if (plazoDias <= 0) {
			throw new RuntimeException("El plazo en días debe ser positivo");
		}
		if (divisa == null || divisa.isEmpty()) { //revisar bien lo de la divisa
			throw new RuntimeException("La divisa no puede ser nula o vacía");
		}

		if (tasa <= 0) {
			throw new RuntimeException("La tasa debe ser positiva");
		}

		Usuario usuario = usuarios.get(dni);
		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}


		Cuenta cuenta = cuentas.get(cvu);
		if (cuenta == null) {
			throw new RuntimeException("La cuenta no existe");
		}

		if (!cuenta.getTitular().getDni().equals(dni)) {//CHEQUEO QUE LA CUENTA SEA DE EL USUARIO
			throw new RuntimeException("El CVU no pertenece al usuario");
		}

		if (cuenta.getSaldoDisponible() < monto) {
			throw new RuntimeException("Saldo insuficiente en la cuenta");
		}

		int id = generarIdInversion();
		VinculadaDivisa nuevaInversion = new VinculadaDivisa(id, monto, cuenta, plazoDias, divisa, tasa);		cuenta.disminuirSaldoDisponible(monto);
		cuenta.aumentarSaldoInvertido(monto);


		cuenta.agregarActividad(nuevaInversion);
		historialGlobal.add(nuevaInversion);
		inversiones.put(id, nuevaInversion);

		usuario.aumentarTotalInvertido(monto);

		return id;



	}

	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		if (dni.length() != 8 || dni.length() != 7)  {
			throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos");
		}
		if (cvu == null || cvu.isEmpty()) {
			throw new IllegalArgumentException("El CVU no puede ser nulo o vacío");
		}
		if (monto <= 0) {
			throw new IllegalArgumentException("El monto debe ser positivo");
		}
		if (plazoDias <= 0) {
			throw new IllegalArgumentException("El plazo en días debe ser positivo");
		}

		for (Usuario usuario : usuarios.values()) {
			for (Cuenta cuenta : usuario.getCuentas() ) {
				if (cuenta.getCvu().equals(cvu)) {
					if (cuenta.getSaldoDisponible() < monto) {
						throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
					}
					cuenta.disminuirSaldoDisponible(monto);
					FondoLiquidez nuevaInversion = new FondoLiquidez(monto, plazoDias);
					cuenta.agregarActividad(nuevaInversion);
				}
			}
		}



	}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		if (dni == null || !(dni.length() == 7 || dni.length() == 8)) {
			throw new RuntimeException("El DNI debe tener 7 u 8 dígitos");
		}

		if (cvu == null || cvu.isEmpty()) {
			throw new RuntimeException("El CVU no puede ser nulo o vacío");
		}

		if (idInversion <= 0) {
			throw new RuntimeException("El ID de inversión debe ser positivo");
		}

		Usuario usuario = usuarios.get(dni);

		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}

		Cuenta cuenta = cuentas.get(cvu);

		if (cuenta == null) {
			throw new RuntimeException("La cuenta no existe");
		}

		if (!cuenta.getTitular().getDni().equals(dni)) {
			throw new RuntimeException("El CVU no pertenece al usuario");
		}

		Inversion inversion = inversiones.get(idInversion);

		if (inversion == null) {
			throw new RuntimeException("La inversión no existe");
		}

		if (!inversion.getCuentaOrigen().getCvu().equals(cvu)) {
			throw new RuntimeException("La inversión no pertenece a esa cuenta");
		}

		if (inversion.estaPrecancelada()) {
			throw new RuntimeException("La inversión ya fue precancelada");
		}

		inversion.cancelar();

		cuenta.disminuirSaldoInvertido(inversion.getMonto());
		cuenta.aumentarSaldoDisponible(inversion.getMonto());
		//ver para hacer que devuelta la mitad del monto
		usuario.disminuirTotalInvertido(inversion.getMonto());
	}



	@Override
	public String consultarCvu(String alias) {
		if (alias == null || alias.isEmpty()) {
			throw new RuntimeException("El alias no puede ser nulo o vacío");
		}

		if (!aliasToCvu.containsKey(alias)) {
			throw new IllegalArgumentException("El alias no existe");
		}

		return aliasToCvu.get(alias);
		}

	@Override
	public List<String> consultarHistorialGlobal() {
		List<String> resultado = new ArrayList<>();

		for (Actividad actividad : historialGlobal) {
			resultado.add(actividad.descripcionOp());
		}

		return resultado;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		if (cvu == null || cvu.isEmpty()) {
			throw new RuntimeException("El CVU no puede ser nulo o vacío");
		}

		Cuenta cuenta = cuentas.get(cvu);

		if (cuenta == null) {
			throw new RuntimeException("La cuenta no existe");
		}

		List<String> resultado = new ArrayList<>();

		for (Actividad actividad : cuenta.getActividades()) {
			resultado.add(actividad.descripcionOp());
		}

		return resultado;
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		if (dniUsuario == null || dniUsuario.isEmpty()) {
			throw new RuntimeException("El DNI no puede ser nulo o vacío");
		}

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}

		List<String> resultado = new ArrayList<>();

		for (Cuenta cuenta : usuario.getCuentas()) {
			for (Actividad actividad : cuenta.getActividades()) {
				resultado.add(actividad.descripcionOp());
			}
		}

		return resultado;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		if (dniUsuario == null || dniUsuario.isEmpty()) {
			throw new RuntimeException("El DNI no puede ser nulo o vacío");
		}

		Usuario usuario = usuarios.get(dniUsuario);

		if (usuario == null) {
			throw new RuntimeException("El usuario no existe");
		}

		return usuario.getTotalInvertido();
	}

}
