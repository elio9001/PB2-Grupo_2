package ar.unlam.tienda;

import java.util.Objects;

import ar.unlam.tienda.enums.Marca;

public abstract class Producto {
	private Integer codProducto;
	private String descripcion;
	private Double precio;
	private Marca marca;
	private Integer stock;
	private Boolean tieneDescuento;

	public Producto(Integer codProducto, String descripcion, Double precio, Marca marca, Integer stock) {
		this.codProducto = codProducto;
		this.descripcion = descripcion;
		this.precio = precio;
		this.marca = marca;
		this.stock = stock;
		this.tieneDescuento=false;
	}
	
	public abstract Double calcularPrecioConDescuento();

	public Integer getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(Integer codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getTieneDescuento() {
		return tieneDescuento;
	}

	public void setTieneDescuento(Boolean tieneDescuento) {
		this.tieneDescuento = tieneDescuento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(codProducto, other.codProducto);
	}

	public void desAgregarStock(Integer cantidad) {
		if (cantidad>=0) {
			stock-=cantidad;
		}
	};
	public abstract Double precioFinal();
	
}
