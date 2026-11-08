package ar.unlam.tienda;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TestElio {
	/**
	 * Carga y control de Stock controlarLaCantidadDeProductosAgregadosAlCarrito
	 * alQuitarUnProductoLaCatidadDeProductosDebeDisminuirEnElCarrito
	 * alRealizarUnaVentaDisminuyeElStock
	 * lanzarCarritoVacioExceptionCuandoSeIntenteComprarSinArticulosEnElCarrito
	 * lanzarProductoNoDisponibleExpection
	 **/
	@Test
	public void controlarLaCantidadDeProductosAgregadosAlCarrito() throws ProductoNoDisponibleException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		Producto produ1 = new Producto(100,20);
		Producto produ2 = new Producto(101,20);
		tienda.agregarProducto(produ1);
		tienda.agregarProducto(produ2);
		
		tienda.agregarProductoAlCarrito(idUser, 100, 5);
		tienda.agregarProductoAlCarrito(idUser, 101, 5);

		Usuario usuarioRegistrado = tienda.buscarUsuarioPorId(idUser);
		assertEquals(2,usuarioRegistrado.getCarrito().size());
	}

	@Test
	public void alQuitarUnProductoLaCatidadDeProductosDebeDisminuirEnElCarrito() throws ProductoNoDisponibleException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		Producto produ1 = new Producto(100,20);
		Producto produ2 = new Producto(101,20);
		
		tienda.agregarProducto(produ1);
		tienda.agregarProducto(produ2);
		
		tienda.agregarProductoAlCarrito(idUser, 100, 5);
		tienda.agregarProductoAlCarrito(idUser, 101, 5);

		Usuario usuarioRegistrado = tienda.buscarUsuarioPorId(idUser);
		usuarioRegistrado.quitarProductoDelCarrito(2); //se ingresa la posicion del producto en el carrito

		assertEquals(1, usuarioRegistrado.getCarrito().size());
	}

	@Test
	public void alRealizarUnaVentaDisminuyeElStock() throws CarritoVacioException, ProductoNoDisponibleException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		Producto produ1 = new Producto(100,22);
		tienda.agregarProducto(produ1);
		
		Integer cantidad = 10;
		tienda.agregarProductoAlCarrito(idUser, 100, cantidad);

		tienda.comprar(123); //ingresa el idUsuario
		
		Integer stockDisponlible = tienda.stockDeUnProducto(100);
		assertEquals(12, stockDisponlible,0.01);
	}

	@Test (expected = CarritoVacioException.class)
	public void lanzarCarritoVacioExceptionCuandoSeIntenteComprarSinArticulosEnElCarrito() throws CarritoVacioException, ProductoNoDisponibleException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		Producto produ1 = new Producto(100,22);
		tienda.agregarProducto(produ1);
		Integer cantidad = 10;

		tienda.comprar(idUser);
	}

	@Test (expected = ProductoNoDisponibleException.class)
	public void lanzarProductoNoDisponibleExpection() throws CarritoVacioException, ProductoNoDisponibleException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		Producto produ1 = new Producto(100,22);
		tienda.agregarProducto(produ1);
		Integer cantidad = 10;
		tienda.agregarProductoAlCarrito(idUser, 999, 5);
	}
}
