package ar.unlam.tienda;

import ar.unlam.tienda.enums.Conexion;
import ar.unlam.tienda.enums.Marca;

public class Periferico extends Producto{
	private Conexion tipoConexion;
	private Boolean poseeRgb;

	public Periferico(Integer codProducto, String descripcion, Double precio, Marca marca, Integer stock,
			Conexion tipoConexion, Boolean poseeRgb) {
		super(codProducto, descripcion, precio, marca, stock);
		this.tipoConexion = tipoConexion;
		this.poseeRgb = poseeRgb;
	}

	// @Override
	public Double calcularPrecioConDescuento() {
		return getPrecio() * 0.30;
	}

	@Override
	public Double precioFinal() {
		if (super.getTieneDescuento()) {
			return super.getPrecio() - calcularPrecioConDescuento();
		}
		return super.getPrecio();
	}
}
