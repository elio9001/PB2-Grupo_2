package ar.unlam.tienda;

public class PrecioNegativoException extends Exception {
	private String mensaje;
	public PrecioNegativoException(String mensaje) {
		this.mensaje = mensaje;
	}

}
