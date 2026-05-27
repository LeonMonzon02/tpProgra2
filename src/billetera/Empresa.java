package billetera;

import java.util.List;
import java.util.ArrayList;

public class Empresa {
	private String cuit;
	private String nombreFantasia;
	private String CorreoElectronico;
	private String nombreContacto;
	private int telefono; 
	private List<Usuario> usuariosEmpresa;
	private boolean estaAutorizado;


	public String getNombreEmpresa() {
		return nombreFantasia;
	}
	
	public String getCuit () {
		return cuit;
	}
	
	public List<Usuario> getUsuarioEmpresa(){
		return usuariosEmpresa;
	}
	
	public void autorizarUsuario(Usuario usuario, String cuit) {
		if 
	}
}
