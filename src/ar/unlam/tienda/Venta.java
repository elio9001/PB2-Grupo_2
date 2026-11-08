package ar.unlam.tienda;

import java.util.HashMap;

public class Venta {
	private static Integer nuevoId=1000;
	private Integer idVenta;
	private Integer idUsuario;
	private HashMap<Integer, ProductoYCantidad> carrito;
	private Double precioFinal;
	public Venta(Integer idUsuario, HashMap<Integer, ProductoYCantidad> carrito) {
		this.idUsuario = idUsuario;
		this.carrito = carrito;
		
	}
}
