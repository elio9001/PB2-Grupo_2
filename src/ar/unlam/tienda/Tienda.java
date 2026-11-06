package ar.unlam.tienda;

import java.util.HashSet;

public class Tienda {
	private HashSet<Usuario> usuarios;
	public Tienda() {
		this.usuarios=new HashSet<>();
	}
	public Boolean registrarUsuario(Usuario usuario) {
		if(validarEmail(usuario)) {
			return this.usuarios.add(usuario);
		}
		return false;
		
	}
	private boolean validarEmail(Usuario usuario) throws CorreoInvalidoException{
		if(usuario.getCorreo()==null || !usuario.getCorreo().contains("@") || !usuario.getCorreo().endsWith(".com")) {
			throw new CorreoInvalidoException ("Correo invalido");
		
		}
		return true;
		
		
	}

}
