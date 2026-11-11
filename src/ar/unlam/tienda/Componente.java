package ar.unlam.tienda;

import ar.unlam.tienda.enums.Marca;
import ar.unlam.tienda.enums.Parte;
import ar.unlam.tienda.interfaces.Garantia;

public class Componente extends Producto implements Garantia{
	private Parte parte;
	private Integer aniosDeGarantia;
	
	public Componente(Integer codProducto, String descripcion, Double precio, Marca marca, Integer stock,
			Parte parte, Integer aniosDeGarantia) {
		super(codProducto, descripcion, precio, marca, stock);
		this.parte = parte;
		this.aniosDeGarantia = aniosDeGarantia;
	}

	@Override
	public Double calcularPrecioConDescuento() {	
		return getPrecio() * 0.50;
	}

	public Double calcularGarantia() {
		return super.getPrecio()*0.1;
	}

	@Override
	public Double precioFinal() {
		if (super.getTieneDescuento()) {
			return (super.getPrecio() + calcularGarantia()) - calcularPrecioConDescuento();
		}
		return (super.getPrecio() + calcularGarantia());
	}
}
