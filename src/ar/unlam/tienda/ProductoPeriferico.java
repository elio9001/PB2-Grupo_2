package ar.unlam.tienda;

import ar.unlam.tienda.enums.Conexion;

public class ProductoPeriferico extends Producto {
	private Conexion conexion;
	private Boolean tieneRgb;

	public ProductoPeriferico(Integer id, String nombre, Double precio, Integer stock, Conexion conexion, Boolean tieneRgb) {
		super(id, nombre, precio, stock);
		this.conexion=conexion;
		this.tieneRgb=tieneRgb;
	}

}
