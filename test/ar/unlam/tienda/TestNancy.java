package ar.unlam.tienda;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

import ar.unlam.tienda.enums.Conexion;

public class TestNancy {
	// alta usuario
	@Test
	public void registrarUnUsuarioCorrectamente()  {
		Tienda tienda = new Tienda();
		Usuario usuario = new Usuario("Nancy", "nancy@gmail.com");
		Boolean seRegistro=tienda.registrarUsuario(usuario);
		assertTrue(seRegistro);
	}

	/* (si no contiene “@” y termina con “.com”) */
	@Test(expected = CorreoInvalidoException.class)
	public void correoInvalidoLanzaExcepcion() throws CorreoInvalidoException{
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
		tienda.registrarUsuario(usuario2);
		assertEquals(1,tienda.cantidadDeUsuariosRegistrados(),0.01);
	}

	@Test
	public void ordenarProductosPorNombreDeFormaAscendete() {
		Tienda tienda=new Tienda();
		Usuario usuario= new Usuario("Nancy", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);
		Producto mouse= new ProductoPeriferico(1, "Mouse Gamer", 40000.0,2,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(mouse);
		Producto auricular= new ProductoPeriferico(2, "Auricular Gamer", 100000.0,3,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(auricular);
		TreeSet<Producto>pOrdenado=tienda.ordenarProductosPorNombreDeFormaAscendente();
		assertEquals("Auricular Gamer",pOrdenado.getFirst().getNombre());
		assertEquals("Mouse Gamer",pOrdenado.getLast().getNombre());
	}

	@Test
	public void ordenarProductosPerifericosPorIdAscendenteYPrecioDescendente()  {
		Tienda tienda=new Tienda();
		Usuario usuario= new Usuario("Nancy", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);
		Producto mouse= new ProductoPeriferico(1, "Mouse Gamer", 40000.0,5,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(mouse);
		Producto auricular= new ProductoPeriferico(2, "Auricular Gamer", 100000.0,3,Conexion.ALAMBRICA,true );
		tienda.agregarProducto(auricular);
		
		TreeSet<ProductoPeriferico>pOrdenado=tienda.ordenarProductosPerifericosPorIdAscenteYPrecioDescendente();
		
		assertEquals(1,pOrdenado.getFirst().getId(),0.01);
		assertEquals(2,pOrdenado.getLast().getId(),0.01);
	}
	@Test(expected = NombreInvalidoException.class)
	public void lanzarNombreInvalidoExceptionCuandoSeIntenteRegistrarUnUsuarioSinNombre () throws NombreInvalidoException {
		Tienda tienda = new Tienda();
		Usuario usuario = new Usuario("", "nancy@gmail.com");
		tienda.registrarUsuario(usuario);

		
	}

}
