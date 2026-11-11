package ar.unlam.tienda;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeSet;

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
	private Usuario usuario1;
	private Usuario usuario2;
	private Usuario usuario3;
	private Usuario usuario4;

	@Before
	public void setUp() {

		tienda = new Tienda();
		mouse = new Periferico(1, "Mouse", 100000., Marca.LOGITECH, 10, Conexion.INALAMBRICA, false);
		teclado = new Periferico(2, "Teclado", 200000., Marca.LOGITECH, 20, Conexion.ALAMBRICA, true);
		procesadorIntel = new Componente(3, "Procesador Intel", 500000., Marca.INTEL, 5, Parte.PROCESADOR, 1);

		usuario1 = new Usuario(123, "nancy@gmail.com", "Nancy");
		usuario2 = new Usuario(124, "alumMaster.com", "Master");
		usuario3 = new Usuario(125, "nadio@gmail.com", "");
	}

	@Test // test 1
	public void agregarCorretamenteUnProducto() throws PrecioNegativoException {

		tienda.agregarProducto(mouse);

		assertEquals(1, tienda.obtenerProductos().size());

	}

	@Test // test 2
	public void darDeBajaUnProductoRegistrado()
			throws ProductoNoDisponibleException, ProductoNoEncontradoException, PrecioNegativoException {

		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);

		assertEquals(3, tienda.obtenerProductos().size());

		tienda.darDeBajaProductoRegistrado(1);

		assertEquals(2, tienda.obtenerProductos().size());
	}

	@Test // test 3
	public void queSePuedaActualizarElPrecioDeUnProducto()
			throws PrecioNegativoException, ProductoNoDisponibleException, ProductoNoEncontradoException {

		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);

		tienda.actualizarPrecioDeUnProducto(1, 110000.);

		Double precioActualizadoEsperado = 110000.;

		assertEquals(precioActualizadoEsperado, mouse.getPrecio());
	}

	@Test(expected = ProductoNoEncontradoException.class) // test 4
	public void lanzarProductoNoEncontradoExceptionCuandoSeIntenteOperarConProductoInexistente()
			throws ProductoNoEncontradoException, PrecioNegativoException, ProductoNoDisponibleException {
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);

		Integer otroCodigoProducto = 5;
		tienda.darDeBajaProductoRegistrado(otroCodigoProducto);

		// tienda.actualizarPrecioDeUnProducto(5, 130000);
	}

	@Test(expected = PrecioNegativoException.class) // test 5
	public void lanzarPrecioNegativoExceptionCuandoSeAgregaOActualizaElPrecioAUnValorNegativo()
			throws PrecioNegativoException, ProductoNoDisponibleException, ProductoNoEncontradoException {

		Producto auricular = new Periferico(4, "Auricular", -300000., Marca.LOGITECH, 2, Conexion.INALAMBRICA, false);

		tienda.agregarProducto(auricular);

		tienda.agregarProducto(mouse);

		tienda.actualizarPrecioDeUnProducto(1, -1.);

	}

	@Test // test 6
	public void controlarLaCantidadDeProductosAgregadosAlCarrito() throws PrecioNegativoException,
			ProductoNoEncontradoException, CorreoInvalidoException, NombreInvalidoException {
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
		assertEquals(2, usuarioRegistrado.getCarrito().size());
	}

	@Test // test 7
	public void alQuitarUnProductoLaCatidadDeProductosDebeDisminuirEnElCarrito() throws PrecioNegativoException,
			ProductoNoEncontradoException, CorreoInvalidoException, NombreInvalidoException {
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
		usuarioRegistrado.quitarProductoDelCarrito(2); // se ingresa la posicion del producto en el carrito

		assertEquals(1, usuarioRegistrado.getCarrito().size());
	}

	@Test // test 8
	public void alRealizarUnaVentaDisminuyeElStock() throws CarritoVacioException, PrecioNegativoException,
			ProductoNoEncontradoException, ProductoNoDisponibleException, StockNoDisponibleExceptcion,
			CorreoInvalidoException, NombreInvalidoException {
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

		tienda.comprar(123); // ingresa el idUsuario, el tiene su propio carrito

		Integer stockDisponlible = tienda.stockDeUnProducto(1);
		assertEquals(3, stockDisponlible, 0.01);
	}

	@Test(expected = CarritoVacioException.class) // test 9
	public void lanzarCarritoVacioExceptionCuandoSeIntenteComprarSinArticulosEnElCarrito()
			throws CarritoVacioException, ProductoNoDisponibleException, PrecioNegativoException,
			StockNoDisponibleExceptcion, CorreoInvalidoException, NombreInvalidoException {
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

	@Test(expected = StockNoDisponibleExceptcion.class) // test 10
	public void lanzarCantidadNoDisponibleCuandoNoHayStockSuficiente()
			throws PrecioNegativoException, ProductoNoEncontradoException, CarritoVacioException,
			StockNoDisponibleExceptcion, CorreoInvalidoException, NombreInvalidoException {
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
	public void calcularElPrecioFinalDelUnCarritoDeProductosConGarantia()
			throws PrecioNegativoException, ProductoNoEncontradoException, CarritoVacioException,
			StockNoDisponibleExceptcion, CorreoInvalidoException, NombreInvalidoException {
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

	@Test // test 12
	public void CalularElPrecioFinalDeUnCarritoConProductosEnDescuento() throws PrecioNegativoException,
			ProductoNoEncontradoException, CorreoInvalidoException, NombreInvalidoException {
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

	@Test // test 13
	public void calcularElPrecioFinalDelCarritoConProductosEnDescuentoYConGarantia() throws PrecioNegativoException,
			ProductoNoEncontradoException, CorreoInvalidoException, NombreInvalidoException {

		Integer idUser = 123;
		String email = "master@gmail.com";
		String username = "master777";
		Usuario user = new Usuario(idUser, email, username);

		tienda.registrarUsuario(user);
		Usuario usuario = tienda.buscarUsuarioPorId(idUser);

		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);

		tienda.habilitarDescuentoAUnProducto(1);
		tienda.habilitarDescuentoAUnProducto(3);

		tienda.agregarProductoAlCarrito(idUser, 1, 2); // precio = 70 * 2 = 140.000
		tienda.agregarProductoAlCarrito(idUser, 2, 2); // precio = 200.000 * 2 = 400.000
		tienda.agregarProductoAlCarrito(idUser, 3, 2);
		Double montoCalculado = tienda.calcularMontoDelCarrito(idUser);

		Double precioFinalEsperado = 1140000.;

		assertEquals(precioFinalEsperado, montoCalculado);
	}

	@Test // test 14
	public void registrarUnUsuarioCorrectamente() throws CorreoInvalidoException, NombreInvalidoException {

		tienda.registrarUsuario(usuario1);
		assertEquals(1, tienda.getUsuarios().size());
	}

	/* (si no contiene “@” y termina con “.com”) */
	@Test(expected = CorreoInvalidoException.class) // test 15
	public void correoInvalidoLanzaExcepcion() throws CorreoInvalidoException, NombreInvalidoException {
		tienda.registrarUsuario(usuario2);
	}

	@Test // test 16
	public void noSePuedenRegistrarDosCuentasConElMismoCorreo()
			throws CorreoInvalidoException, NombreInvalidoException {

		tienda.registrarUsuario(usuario1);
		tienda.registrarUsuario(usuario1);
		assertEquals(1, tienda.getUsuarios().size(), 0.01);
	}

	@Test(expected = NombreInvalidoException.class) // test 17
	public void lanzarNombreInvalidoExceptionCuandoSeIntenteRegistrarUnUsuarioSinNombre()
			throws NombreInvalidoException, CorreoInvalidoException {

		tienda.registrarUsuario(usuario3);

	}

	@Test // test 18
	public void ordenarProductosPorNombreDeFormaAscendete() throws PrecioNegativoException {

		tienda.agregarProducto(mouse);

		tienda.agregarProducto(teclado);
		TreeSet<Producto> pOrdenado = tienda.ordenarProductosPorNombreDeFormaAscendente();
		assertEquals("Mouse", pOrdenado.getFirst().getDescripcion());
		assertEquals("Teclado", pOrdenado.getLast().getDescripcion());
	}

	@Test // test 19
	public void ordenarProductosPerifericosPorIdAscendenteYPrecioDescendente() throws PrecioNegativoException {

		tienda.agregarProducto(teclado);

		tienda.agregarProducto(mouse);

		TreeSet<Periferico> pOrdenado = tienda.ordenarProductosPerifericosPorIdAscenteYPrecioDescendente();

		assertEquals(1, pOrdenado.getFirst().getCodProducto(), 0.01);
		assertEquals(2, pOrdenado.getLast().getCodProducto(), 0.01);
	}

	@Test // test 20
	public void obtenerUnReporteDeVentasPorIdDeUsuario() throws CorreoInvalidoException, NombreInvalidoException,
			PrecioNegativoException, ProductoNoEncontradoException, CarritoVacioException, StockNoDisponibleExceptcion {

		tienda.registrarUsuario(usuario1);

		tienda.agregarProducto(mouse);

		Integer cantidad = 2;
		tienda.agregarProductoAlCarrito(123, 1, cantidad);

		tienda.comprar(123); // ingresa el idUsuario

		// segundo producto

		tienda.agregarProducto(teclado);
		tienda.agregarProductoAlCarrito(123, 2, 1);
		tienda.comprar(123);

		Map<Integer, Double> reporteDeVentas = tienda.obtenerReporteDeVentasPorIdDeUsuario();

		Double posiblePrecioTotalVentasDeUsuario1 = 400000.0;

		assertEquals(posiblePrecioTotalVentasDeUsuario1, reporteDeVentas.get(123));

	}

}
