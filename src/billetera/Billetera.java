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
	
		if (aliasToCvu.containsKey(alias)) {
	        throw new IllegalArgumentException("El alias ya existe");
		}
		
		Usuario usuario = buscarUsuario(dniUsuario);
		
		String cvu = Utilitarios.generarSiguienteCvu();
	    
		CuentaRegular nuevaCuenta =
	            new CuentaRegular(cvu, alias, 0, Utilitarios.hoy());

	    cuentas.put(cvu, nuevaCuenta);

	    aliasToCvu.put(alias, cvu);

	    usuario.agregarCuenta(nuevaCuenta);

	    	return cvu;
	}

	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
		if (aliasToCvu.containsKey(alias)) {
	        throw new IllegalArgumentException("El alias ya existe");
		}
		if(depositoInicial<1000000) {
			throw new IllegalArgumentException("El deposito es insufucuente");
			
		}
		Usuario usuario = buscarUsuario(dniUsuario);
		
		String cvu = Utilitarios.generarSiguienteCvu();
		CuentaPremium nuevaCuenta = new CuentaPremium(cvu, alias,depositoInicial, Utilitarios.hoy());
		
		cuentas.put(cvu, nuevaCuenta);
		aliasToCvu.put(alias, cvu);
		
		usuario.agregarCuenta(nuevaCuenta);
		
			return cvu;
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
	    if (!empresas.containsKey(cuitEmpresa)) {
	        throw new IllegalArgumentException("la empresa no existe");
	    }

	    if (!usuarioExiste(dniUsuario)) {
	        throw new IllegalArgumentException("el usuario no existe");
	    }
	    Empresa empresa = empresas.get(cuitEmpresa);
	    
	    if (!empresa.estaAutorizado(dniUsuario)) {
	    	throw new IllegalArgumentException("El usuario no está autorizado");
	    }
	    if (aliasToCvu.containsKey(alias)) {
	        throw new IllegalArgumentException("El alias ya existe");
		}
		
		Usuario usuario = buscarUsuario(dniUsuario);
		
		String cvu = Utilitarios.generarSiguienteCvu();
	    
		CuentaCorporativa nuevaCuenta =
	            new CuentaCorporativa(cvu, alias,0, Utilitarios.hoy(),cuitEmpresa);

	    cuentas.put(cvu, nuevaCuenta);

	    aliasToCvu.put(alias, cvu);
	    usuario.agregarCuenta(nuevaCuenta);
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


		for (Usuario usuario : usuarios.values()) {
			for (Cuenta cuenta : usuario.getCuentas()) {
				if (cuenta.getCvu().equals(cvuOrigen)) {
					if (cuenta.getSaldoDispoinible() < monto) {
						throw new RuntimeException("Saldo insuficiente en la cuenta de origen");
					}
					cuenta.disminuirSaldoDisponible(monto);
					billetera.agregarActividad(new Transferencia(monto, cuenta, null)); // Cuenta destino se asignará después
				}
				if (cuenta.getCvu().equals(cvuDestino)) {
					cuenta.aumentarSaldoDisponible(monto);
					billetera.agregarActividad(new Transferencia(monto, null, cuenta)); // Cuenta origen se asignará después
				}
			}
		}

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
		if (divisa == null || divisa.isEmpty()) { //revisar bien lo de la divisa
			throw new IllegalArgumentException("La divisa no puede ser nula o vacía");
		}

		if (tasa <= 0) {
			throw new IllegalArgumentException("La tasa debe ser positiva");
		}

		for (Usuario usuario : usuarios.values()) {
			for (Cuenta cuenta : usuario.getCuentas() ) {
				if (cuenta.getCvu().equals(cvu)) {
					if (cuenta.getSaldoDisponible() < monto) {
						throw new IllegalArgumentException("Saldo insuficiente en la cuenta");
					}
					cuenta.disminuirSaldoDisponible(monto);
					VinculadaDivisa nuevaInversion = new VinculadaDivisa(monto, plazoDias, divisa, tasa);
					cuenta.agregarActividad(nuevaInversion);
				}
			}
		}

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
		if (dni.length() != 8 || dni.length() != 7)  {
			throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos");
		}
		if (cvu == null || cvu.isEmpty()) {
			throw new IllegalArgumentException("El CVU no puede ser nulo o vacío");
		}
		if (idInversion <= 0) {
			throw new IllegalArgumentException("El ID de inversión debe ser positivo");
		}



		for (Usuario usuario : usuarios.values()) {
			for (Cuenta cuenta : usuario.getCuentas()) {
				if (cuenta.getCvu().equals(cvu)) {
					for (Actividad actividades : cuenta.getActividades().values()) {
						if (si el id de la actividad es igual al idInversion) {
							if (si es rentafija.es precancelable true) {
								Rentafija.cancelar();
							}
							if (si es vinculadadivisa) {
								VinculadaDivisa.cancelar();
							}
							if (si es fondoliquidez) {
								throw new IllegalArgumentException("Las inversiones de fondo de liquidez no son precancelables");
							}

						}
					}
				}
			}
		}
	}

	@Override
	public String consultarCvu(String alias) {
		   if (!aliasToCvu.containsKey(alias)) {
		        throw new IllegalArgumentException("El alias no existe");
		    }

		    return aliasToCvu.get(alias);
		}

	@Override
	public List<String> consultarHistorialGlobal() {
		for (String actividad : historialGlobal) {

		}
		return historialGlobal;
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

}
