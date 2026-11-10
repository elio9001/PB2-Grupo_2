package ar.unlam.tienda;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.unlam.tienda.enums.Conexion;
import ar.unlam.tienda.enums.Marca;
import ar.unlam.tienda.enums.Parte;

public class TestTienda {
	private Tienda tienda;
	private Producto mouse;
	private Producto teclado;
	private Producto procesadorIntel;

	@Before
	public void setUp() {
		
		tienda = new Tienda();
		
		mouse = new Periferico (1, "Mouse", 100000., Marca.LOGITECH,10, Conexion.INALAMBRICA, false);
		
		teclado = new Periferico (2, "Teclado", 200000., Marca.LOGITECH,20, Conexion.ALAMBRICA, true);
		
		procesadorIntel = new Componente (3, "Procesador Intel", 500000., Marca.INTEL, 5, Parte.PROCESADOR, 1);
	}
	
	@Test // test 1
	public void agregarCorretamenteUnProducto() throws PrecioNegativoException {
		
		tienda.agregarProducto(mouse);
		
		assertEquals(1, tienda.obtenerProductos().size());
	
	}
	
	@Test //test 2
	public void darDeBajaUnProductoRegistrado() throws ProductoNoDisponibleException, ProductoNoEncontradoException, PrecioNegativoException { 
		
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);
	
		assertEquals(3, tienda.obtenerProductos().size());
		
		tienda.darDeBajaProductoRegistrado(1);
		
		assertEquals(2, tienda.obtenerProductos().size());
	}
	
	@Test // test 3
	public void queSePuedaActualizarElPrecioDeUnProducto() throws PrecioNegativoException, ProductoNoDisponibleException, ProductoNoEncontradoException {

		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		
		tienda.actualizarPrecioDeUnProducto(1, 110000.);
		
		Double precioActualizadoEsperado = 110000.;
		
		assertEquals(precioActualizadoEsperado, mouse.getPrecio());
	}
	
	@Test (expected = ProductoNoEncontradoException.class) //test 4
	public void lanzarProductoNoEncontradoExceptionCuandoSeIntenteOperarConProductoInexistente() throws ProductoNoEncontradoException, PrecioNegativoException, ProductoNoDisponibleException{
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);
		
		Integer otroCodigoProducto = 5;
		tienda.darDeBajaProductoRegistrado(otroCodigoProducto);
		
		//tienda.actualizarPrecioDeUnProducto(5, 130000);
	}
	
	@Test (expected = PrecioNegativoException.class) // test 5
	public void lanzarPrecioNegativoExceptionCuandoSeAgregaOActualizaElPrecioAUnValorNegativo() throws PrecioNegativoException, ProductoNoDisponibleException, ProductoNoEncontradoException {
	
		Producto auricular =new Periferico(4, "Auricular", -300000., Marca.LOGITECH, 2, Conexion.INALAMBRICA, false);
		
		tienda.agregarProducto(auricular);
		
		tienda.agregarProducto(mouse);
		
		tienda.actualizarPrecioDeUnProducto(1, -1.);
		
	}
	// --- //
	@Test //test 6
	public void controlarLaCantidadDeProductosAgregadosAlCarrito() throws PrecioNegativoException, ProductoNoEncontradoException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		
		tienda.agregarProductoAlCarrito(idUser, 1, 5);
		tienda.agregarProductoAlCarrito(idUser, 2, 5);

		Usuario usuarioRegistrado = tienda.buscarUsuarioPorId(idUser);
		assertEquals(2,usuarioRegistrado.getCarrito().size());
	}

	@Test // test 7
	public void alQuitarUnProductoLaCatidadDeProductosDebeDisminuirEnElCarrito() throws PrecioNegativoException, ProductoNoEncontradoException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		
		tienda.agregarProductoAlCarrito(idUser, 1, 5);
		tienda.agregarProductoAlCarrito(idUser, 2, 5);

		Usuario usuarioRegistrado = tienda.buscarUsuarioPorId(idUser);
		usuarioRegistrado.quitarProductoDelCarrito(2); //se ingresa la posicion del producto en el carrito

		assertEquals(1, usuarioRegistrado.getCarrito().size());
	}

	@Test // test 8
	public void alRealizarUnaVentaDisminuyeElStock() throws CarritoVacioException, PrecioNegativoException, ProductoNoEncontradoException, ProductoNoDisponibleException, StockNoDisponibleExceptcion {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(123);
		
		tienda.agregarProducto(mouse);
		
		Integer cantidad = 7;
		tienda.agregarProductoAlCarrito(idUser, 1, cantidad);

		tienda.comprar(123); //ingresa el idUsuario
		
		Integer stockDisponlible = tienda.stockDeUnProducto(1);
		assertEquals(3, stockDisponlible,0.01);
	}

	@Test (expected = CarritoVacioException.class) // test 9
	public void lanzarCarritoVacioExceptionCuandoSeIntenteComprarSinArticulosEnElCarrito() throws CarritoVacioException, ProductoNoDisponibleException, PrecioNegativoException, StockNoDisponibleExceptcion {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		tienda.agregarProducto(mouse);

		tienda.comprar(idUser);
	}

	
	@Test (expected = StockNoDisponibleExceptcion.class) // test 10
	public void lanzarCantidadNoDisponibleCuandoNoHayStockSuficiente () throws PrecioNegativoException, ProductoNoEncontradoException, CarritoVacioException, StockNoDisponibleExceptcion {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		tienda.agregarProducto(mouse);
		tienda.agregarProductoAlCarrito(idUser, 1, 999);
		tienda.comprar(idUser);
	}
	@Test // test 11
	public void calcularElPrecioFinalDelUnCarritoDeProductosConGarantia() throws PrecioNegativoException, ProductoNoEncontradoException, CarritoVacioException, StockNoDisponibleExceptcion {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		tienda.agregarProducto(procesadorIntel);
		tienda.agregarProductoAlCarrito(idUser, 3, 2);
		Double montoCalculado = tienda.calcularMontoDelCarrito(123);
		Double montoEsperado = 1100000.;
		assertEquals(montoEsperado, montoCalculado);
	}
	@Test //test 12
	public void CalularElPrecioFinalDeUnCarritoConProductosEnDescuento () throws PrecioNegativoException, ProductoNoEncontradoException {
		Tienda tienda = new Tienda();

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);
		
		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);
		
		tienda.agregarProducto(teclado);
		tienda.habilitarDescuentoAUnProducto(2);
		tienda.agregarProductoAlCarrito(idUser, 2, 2);
		Double montoCalculado = tienda.calcularMontoDelCarrito(123);
		Double montoEsperado = 280000.;
		assertEquals(montoEsperado, montoCalculado);
	}
	
	@Test //test 12
	public void calcularElPrecioFinalDelCarritoConProductosEnDescuentoYConGarantia () throws PrecioNegativoException, ProductoNoEncontradoException {
	
	}
}
