package ar.unlam.tienda;

public class ProductoNoEncontradoException extends Exception {
	private String mensaje;
	public ProductoNoEncontradoException(String mensaje) {
		this.mensaje = mensaje;
	}

}
