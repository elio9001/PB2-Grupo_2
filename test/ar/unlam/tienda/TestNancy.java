package ar.unlam.tienda;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

import ar.unlam.tienda.enums.Conexion;

public class TestNancy {
	// alta usuario
	@Test
	public void registrarUnUsuarioCorrectamente() {
		Tienda tienda = new Tienda();
		Usuario usuario = new Usuario("Nancy", "nancy@gmail.com");
		Boolean seRegistro=tienda.registrarUsuario(usuario);
		assertTrue(seRegistro);
	}

	/* (si no contiene “@” y termina con “.com”) */
	@Test(expected = CorreoInvalidoException.class)
	public void correoInvalidoLanzaExcepcion() throws CorreoInvalidoException {
		Tienda tienda = new Tienda();
		Usuario usuario = new Usuario("Nancy", "nancyAgmail.ar");
		tienda.registrarUsuario(usuario);
	}

	@Test
	public void noSePuedenRegistrarDosCuentasConElMismoCorreo() {
		Tienda tienda = new Tienda();
		Usuario usuario = new Usuario("Nancy", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);
		Usuario usuario2 = new Usuario("Nancy", "nancy@gmail.com");
		Boolean seRegistro2=tienda.registrarUsuario(usuario2);
		assertFalse(seRegistro2);
	}
  /*test18*/
	@Test
	public void ordenarProductosPorNombreDeFormaAscendete() {
		Tienda tienda=new Tienda();
		Usuario usuario= new Usuario("Nancy", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);
		Producto mouse= new ProductoPeriferico(1, "Mouse Gamer", 40000.0,2,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(mouse);
		Producto auricular= new ProductoPeriferico(1, "Auricular Gamer", 100000.0,3,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(auricular);
		TreeSet<Producto>prodOrdenadoPorNombre=tienda.ordenarProductosPorNombreDeFormaAscendente();
		assertEquals("Auricular Gamer",prodOrdenadoPorNombre.first().getNombre());
		assertEquals("Mouse Gamer",prodOrdenadoPorNombre.last().getNombre());
		

	}

	@Test
	public void ordenarLasVentasPorIdAscendenteYPrecioDescendente() {
		/*Tienda tienda=new Tienda();
		Usuario usuario= new Usuario("Nancy", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);
		Producto mouse= new ProductoPeriferico(1, "Mouse Gamer", 40000.0,2,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(mouse);
		Producto auricular= new ProductoPeriferico(2, "Auricular Gamer", 100000.0,3,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(auricular);
		
		Integer cantidad = 10;
		tienda.agregarProductoAlCarrito(1, 1, cantidad);

		tienda.comprar(1);
		
		TreeSet<Producto>prodOrdenadoPorNombre=tienda.ordenarProductosPorNombreDeFormaAscendente();
		assertEquals("Auricular Gamer",prodOrdenadoPorNombre.first().getNombre());
		assertEquals("Mouse Gamer",prodOrdenadoPorNombre.last().getNombre());
*/
	}
	@Test
	public void lanzarNoHayVentasException () {

	}

}
