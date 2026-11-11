package ar.unlam.tienda;

public class CorreoInvalidoException extends Exception {
	private String mensaje;
	public CorreoInvalidoException(String mensaje) {
		this.mensaje=mensaje;	}

}
