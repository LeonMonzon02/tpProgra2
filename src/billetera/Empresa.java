package billetera;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Empresa {
	private String cuit;
	private String nombreFantasia;
	private String email;
	private String nombreContacto;
	private String telefono; 
	private Set<String> usuariosAutorizados;
	private boolean estaAutorizado;

	public Empresa(String cuit, String nombre,String email, String telefono, String nombreContacto ) {
	    this.cuit = cuit;
	    this.nombreFantasia = nombre;
	    this.usuariosAutorizados = new HashSet<>();
	}
	
	
	public void agregarAutorizado(String dni) {
		    usuariosAutorizados.add(dni);
		}
	
	public boolean estaAutorizado(String dni) {
		return usuariosAutorizados.contains(dni);
	}
	
	
	public String getNombreEmpresa() {
		return nombreFantasia;
	}
	
	public String getCuit () {
		return cuit;
	}
	
	public Set<String> getUsuariosAutorizados(){
		return usuariosAutorizados;
	}
	
}
