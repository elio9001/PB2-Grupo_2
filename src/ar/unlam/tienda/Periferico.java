package ar.unlam.tienda;

import ar.unlam.tienda.enums.Conexion;
import ar.unlam.tienda.enums.Marca;
import ar.unlam.tienda.interfaces.Ofertable;

public class Periferico extends Producto{
	
	private Conexion tipoConexion;
	private Boolean poseeRgb;

	public Periferico(Integer codProducto, String descripcion, Integer precio, Marca marca, Integer stock,
			Conexion tipoConexion, Boolean poseeRgb) {
		super(codProducto, descripcion, precio, marca, stock);
		this.tipoConexion = tipoConexion;
		this.poseeRgb = poseeRgb;
	}

	//@Override
	public Double calcularPrecioConDescuento() {
		return getPrecio() * 0.70 ;
	}

}
