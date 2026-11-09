package ar.unlam.tienda;

public class PrecioNegativoException extends RuntimeException {

	public PrecioNegativoException (String mensaje) {
		super(mensaje);
	}
}
