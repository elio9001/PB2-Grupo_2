package ar.unlam.tienda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.unlam.tienda.enums.Conexion;
import ar.unlam.tienda.enums.Marca;
import ar.unlam.tienda.enums.Parte;

public class TestDylan {
	
	private Tienda tienda;
	private Producto mouse;
	private Producto teclado;
	private Producto procesadorIntel;

	@Before
	public void setUp() {
		
		tienda = new Tienda();
		
		mouse = new Periferico (1, "Mouse", 100000, Marca.LOGITECH,10, Conexion.INALAMBRICA, false);
		
		teclado = new Periferico (2, "Teclado", 200000, Marca.LOGITECH,20, Conexion.ALAMBRICA, true);
		
		procesadorIntel = new Componente (3, "Procesador Intel", 500000, Marca.INTEL, 5, Parte.PROCESADOR, 1);
	}
	
	@Test
	public void agregarCorretamenteUnProducto() {
		
		tienda.agregarProducto(mouse);
		
		assertEquals(1, tienda.obtenerProductos().size());
	
	}
	
	@Test
	public void darDeBajaUnProductoRegistrado() {
		
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);
	
		assertEquals(3, tienda.obtenerProductos().size());
		
		tienda.darDeBajaProductoRegistrado(1);
		
		assertEquals(2, tienda.obtenerProductos().size());
	}
	
	@Test
	public void queSePuedaActualizarElPrecioDeUnProducto() {

		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		
		tienda.actualizarPrecioDeUnProducto(1, 110000);
		
		Integer precioActualizadoEsperado = 110000;
		
		assertEquals(precioActualizadoEsperado, mouse.getPrecio());
	}
	
	@Test (expected = ProductoNoEncontradoException.class)
	public void lanzarProductoNoEncontradoExceptionCuandoSeIntenteOperarConProductoInexistente() throws ProductoNoEncontradoException{
		tienda.agregarProducto(mouse);
		tienda.agregarProducto(teclado);
		tienda.agregarProducto(procesadorIntel);
		
		tienda.darDeBajaProductoRegistrado(4);
		tienda.actualizarPrecioDeUnProducto(5, 130000);
	}
	
	@Test (expected = PrecioNegativoException.class)
	public void lanzarPrecioNegativoExceptionCuandoSeAgregaOActualizaElPrecioAUnValorNegativo() throws PrecioNegativoException {
	
		Producto auricular =new Periferico(4, "Auricular", -300000, Marca.LOGITECH, 2, Conexion.INALAMBRICA, false);
		
		tienda.agregarProducto(auricular);
		
		tienda.agregarProducto(mouse);
		
		tienda.actualizarPrecioDeUnProducto(1, -1);
		
	}
}
