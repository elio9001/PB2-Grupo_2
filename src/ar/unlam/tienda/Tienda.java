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

	public void comprar(Integer idUsuario) throws CarritoVacioException, StockNoDisponibleExceptcion{
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		validaciones(usuario);
		Venta nuevaVenta = new Venta(usuario.getIdUsuario(), usuario.getCarrito());
		this.ventas.add(nuevaVenta);
		actualizarStock(usuario.getCarrito());
		vaciarCarrito(usuario);
	}

	public void validaciones(Usuario usuario) throws CarritoVacioException, StockNoDisponibleExceptcion{
		validarSiElCarritoEstaVacio(usuario.getCarrito());
		validarSiHayStockDisponible(usuario.getCarrito());
	}

	private void validarSiHayStockDisponible(HashMap<Integer, ProductoYCantidad> carrito) throws StockNoDisponibleExceptcion
			 {
		for (Producto producto : productos) {
			for (Map.Entry<Integer, ProductoYCantidad> entry : carrito.entrySet()) {
				Integer key = entry.getKey();
				ProductoYCantidad val = entry.getValue();
				if (producto.getCodProducto().equals(val.getProducto().getCodProducto())) {
					if (val.getCantidad() > producto.getStock()) {
						 throw new StockNoDisponibleExceptcion();
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
				if (producto.getCodProducto().equals(val.getProducto().getCodProducto())) {
					producto.desAgregarStock(val.getCantidad());
				}
			}
		}

	}

	public Integer stockDeUnProducto(Integer codProducto) throws ProductoNoEncontradoException {
		Producto producto = buscarProductoPorCodigo(codProducto);
		return producto.getStock();
	}

	private Producto buscarProductoPorCodigo(Integer codigo) throws ProductoNoEncontradoException {
		for (Producto producto : productos) {
			if (producto.getCodProducto().equals(codigo)) {
				return producto;
			}
		}
		throw new ProductoNoEncontradoException("el producto buscado no existe");
	}

	public void agregarProducto(Producto producto) throws PrecioNegativoException {
		if (producto.getPrecio() < 0) {
			throw new PrecioNegativoException("El precio debe ser un valor positivo");
		}
		this.productos.add(producto);
	}

	public void vaciarCarrito(Usuario usuario) {
		usuario.vaciarCarrito();
	}

	public void agregarProductoAlCarrito(Integer idUsuario, Integer codProducto, Integer cantidad) throws ProductoNoEncontradoException
			{
		Usuario usuario = buscarUsuarioPorId(idUsuario);
		Producto producto = buscarProductoPorCodigo(codProducto);
		usuario.agregarPruductoAlCarrito(producto, cantidad);
	}

	public HashSet<Producto> obtenerProductos() {
		return this.productos;
	}

	public void darDeBajaProductoRegistrado(Integer codProducto)
			throws ProductoNoDisponibleException, ProductoNoEncontradoException {
		Producto productoADarDeBaja = buscarProductoPorCodigo(codProducto);
		//if (productoADarDeBaja != null) {
			this.productos.remove(productoADarDeBaja);
		//}
//		if (productoADarDeBaja == null) {
//			throw new ProductoNoEncontradoException("No se ha podido encontrar el producto o es inexistente");
//		}
	}

	public void actualizarPrecioDeUnProducto(Integer codProducto, Integer nuevoPrecio)
			throws ProductoNoDisponibleException, ProductoNoEncontradoException, PrecioNegativoException {
		Producto productoAActualizarDePrecio = buscarProductoPorCodigo(codProducto);

		if (productoAActualizarDePrecio == null) {
			throw new ProductoNoEncontradoException("No se ha podido encontrar el producto o es inexistente");
		}

		if (nuevoPrecio < 0) {
			throw new PrecioNegativoException("El precio debe ser un valor positivo");
		}

		if (productoAActualizarDePrecio != null) {
			productoAActualizarDePrecio.setPrecio(nuevoPrecio);
		}
	}

}
