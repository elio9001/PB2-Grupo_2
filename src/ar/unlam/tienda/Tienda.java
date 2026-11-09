package ar.unlam.tienda;

import java.util.HashSet;
import java.util.Set;

import ar.unlam.tienda.Producto;

public class Tienda {

	private Set<Producto> productos;
	
	public Tienda() {
		this.productos = new HashSet<Producto>();
	}

	public void agregarProducto(Producto producto1) {
		if(producto1.getPrecio() < 0 ) {
			throw new PrecioNegativoException("El precio debe ser un valor positivo");
		}
		this.productos.add(producto1);
	}
	
	public Set<Producto> obtenerProductos(){
		return this.productos;
	}
	
	private Producto buscarProducto(Integer codProducto) {
		for (Producto producto : productos) {
			if (producto.getCodProducto().equals(codProducto)) {
				return producto;
			}
	}
		return null;
	}

	public void darDeBajaProductoRegistrado(Integer codProducto) throws ProductoNoEncontradoException {
		Producto productoADarDeBaja = buscarProducto(codProducto);
		if(productoADarDeBaja != null) {
			this.productos.remove(productoADarDeBaja);
		}
		if (productoADarDeBaja == null) {
			throw new ProductoNoEncontradoException("No se ha podido encontrar el producto o es inexistente");
		}
		
	}

	public void actualizarPrecioDeUnProducto(Integer codProducto, Integer nuevoPrecio) throws ProductoNoEncontradoException,
				PrecioNegativoException{
		Producto productoAActualizarDePrecio = buscarProducto(codProducto);
		
		if (productoAActualizarDePrecio == null) {
			throw new ProductoNoEncontradoException("No se ha podido encontrar el producto o es inexistente");
		}
		
		if(nuevoPrecio < 0 ) {
			throw new PrecioNegativoException("El precio debe ser un valor positivo");
		}
		
		if(productoAActualizarDePrecio != null) {
			productoAActualizarDePrecio.setPrecio(nuevoPrecio);
		}
	}
}