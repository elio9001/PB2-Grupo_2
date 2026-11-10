package ar.unlam.tienda;

import ar.unlam.tienda.enums.Marca;
import ar.unlam.tienda.enums.Parte;

public class Componente extends Producto{
	private Parte parte;
	private Integer aniosDeGarantia;
	
	public Componente(Integer codProducto, String descripcion, Integer precio, Marca marca, Integer stock,
			Parte parte, Integer aniosDeGarantia) {
		super(codProducto, descripcion, precio, marca, stock);
		this.parte = parte;
		this.aniosDeGarantia = aniosDeGarantia;
	}

	@Override
	public Double calcularPrecioConDescuento() {	
		return getPrecio() * 0.50;
	}
}
