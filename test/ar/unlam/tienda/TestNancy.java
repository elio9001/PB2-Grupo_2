package ar.unlam.tienda;

import static org.junit.Assert.*;

import org.junit.Test;

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

	}

	@Test
	public void ordenarProductosPorNombreDeFormaAscendete() {

	}

	@Test
	public void ordenarLasVentasPorIdAscendenteYPrecioDescendente() {

	}

}
