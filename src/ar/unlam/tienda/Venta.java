package ar.unlam.tienda;

import java.util.HashMap;

public class Venta {
	private static Integer nuevoId=1000;
	private Integer idVenta;
	private Integer idUsuario;
	private HashMap<Integer, ProductoYCantidad> carrito;
	private Double precioFinal;
	public Venta(Integer idUsuario, HashMap<Integer, ProductoYCantidad> carrito,Double precioFinal) {
		this.idUsuario = idUsuario;
		this.carrito = carrito;
		this.precioFinal=precioFinal;
		this.idVenta=nuevoId;
		nuevoId++;
	}

	public Double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(Double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public HashMap<Integer, ProductoYCantidad> getCarrito() {
		return carrito;
	}

	public void setCarrito(HashMap<Integer, ProductoYCantidad> carrito) {
		this.carrito = carrito;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
