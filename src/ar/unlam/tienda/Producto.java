package ar.unlam.tienda;

import java.util.Objects;

public class Producto {
	private Integer stock;
	private Integer codigo;
	
	public Producto (Integer codigo, Integer stock) {
		this.stock = stock;
		this.codigo = codigo;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void desAgregarStock(Integer cantidad) {
		if (cantidad>=0) {
			stock-=cantidad;
		}
	};

	public Integer getCodigo() {
		// TODO Auto-generated method stub
		return codigo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return Objects.equals(codigo, other.codigo);
	}
	
	
}
