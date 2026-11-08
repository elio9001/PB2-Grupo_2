package ar.unlam.tienda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Tienda {
	HashSet<Usuario> usuarios;
	ArrayList<Venta> ventas;
	HashSet<Producto> productos;

	public Tienda() {
		this.usuarios = new HashSet<Usuario>();
		this.ventas = new ArrayList<Venta>();
		this.productos = new HashSet<Producto>();

	}

	public void registrarUsuario(Usuario user) {
		this.usuarios.add(user);
	}

	public Usuario buscarUsuarioPorId(Integer idUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getIdUsuario().equals(idUsuario)) {
				return usuario;
			}
		}
		return null;
	}

	public void comprar(Integer idUsuario) throws CarritoVacioException, ProductoNoDisponibleException {
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		validaciones(usuario);
		Venta nuevaVenta = new Venta(usuario.getIdUsuario(), usuario.getCarrito());
		this.ventas.add(nuevaVenta);
		actualizarStock(usuario.getCarrito());
		vaciarCarrito(usuario);
	}

	public void validaciones(Usuario usuario) throws CarritoVacioException, ProductoNoDisponibleException {
		validarSiElCarritoEstaVacio(usuario.getCarrito());
		validarSiHayStockDisponible(usuario.getCarrito());
	}

	private void validarSiHayStockDisponible(HashMap<Integer, ProductoYCantidad> carrito)
			throws ProductoNoDisponibleException {
		for (Producto producto : productos) {
			for (Map.Entry<Integer, ProductoYCantidad> entry : carrito.entrySet()) {
				Integer key = entry.getKey();
				ProductoYCantidad val = entry.getValue();
				if (producto.getCodigo().equals(val.getProducto().getCodigo())) {
					if (val.getCantidad() > producto.getStock()) {
						// throw new ProductoNoDisponibleException();
					}
				}
			}
		}

	}

	public void validarSiElCarritoEstaVacio(HashMap<Integer, ProductoYCantidad> carrito) throws CarritoVacioException {
		if (carrito.size() <= 0) {
			throw new CarritoVacioException();
		}
	}

	private void actualizarStock(HashMap<Integer, ProductoYCantidad> carrito) {
		for (Producto producto : productos) {
			for (Map.Entry<Integer, ProductoYCantidad> entry : carrito.entrySet()) {
				Integer key = entry.getKey();
				ProductoYCantidad val = entry.getValue();
				if (producto.getCodigo().equals(val.getProducto().getCodigo())) {
					producto.desAgregarStock(val.getCantidad());
				}
			}
		}

	}

	public Integer stockDeUnProducto(Integer codProducto) throws ProductoNoDisponibleException {
		Producto producto = buscarProductoPorCodigo(codProducto);
		return producto.getStock();
	}

	private Producto buscarProductoPorCodigo(Integer codigo) throws ProductoNoDisponibleException {
		for (Producto producto : productos) {
			if (producto.getCodigo().equals(codigo)) {
				return producto;
			}
		}
		throw new ProductoNoDisponibleException();
	}

	public void agregarProducto(Producto produ1) {
		this.productos.add(produ1);
	}

	public void vaciarCarrito(Usuario usuario) {
		usuario.vaciarCarrito();
	}

	public void agregarProductoAlCarrito(Integer idUsuario, Integer codProducto, Integer cantidad)
			throws ProductoNoDisponibleException {
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		Producto producto = buscarProductoPorCodigo(codProducto);
		usuario.agregarPruductoAlCarrito(producto, cantidad);
	}

}
