package ar.unlam.tienda;

import java.util.Objects;

public abstract class Producto {
	
	private Integer id;
	private String nombre;
	private Double precio;
	private Integer stock;

	public Producto(Integer id, String nombre, Double precio, Integer stock) {
		this.id=id;
		this.nombre=nombre;
		this.precio=precio;
		this.stock=stock;
	
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}


	
}
