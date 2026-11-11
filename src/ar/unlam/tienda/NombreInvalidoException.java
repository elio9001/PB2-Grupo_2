package ar.unlam.tienda;

public class NombreInvalidoException extends Exception {
	private String mensaje;
	public NombreInvalidoException(String mensaje) {
		this.mensaje = mensaje;
	}

}
