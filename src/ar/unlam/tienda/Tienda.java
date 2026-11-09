package ar.unlam.tienda;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Tienda {
	private HashSet<Usuario> usuarios;
	private HashSet<Producto> productos;
	private ArrayList<Venta> ventas;
	public Tienda() {
		this.usuarios=new HashSet<>();
		this.productos=new HashSet<>();
		this.ventas = new ArrayList<Venta>();
	}
	public Boolean registrarUsuario(Usuario usuario) {
		if(validarEmail(usuario)) {
			return this.usuarios.add(usuario);
		}
		return false;
		
	}
	private boolean validarEmail(Usuario usuario) throws CorreoInvalidoException{
		if(usuario.getCorreo()==null || !usuario.getCorreo().contains("@") || !usuario.getCorreo().endsWith(".com")) {
			throw new CorreoInvalidoException ("Correo invalido");
		
		}
		return true;
		
		
	}
	public void agregarProducto(Producto prod) {
		this.productos.add(prod);
		
	}
	public TreeSet<Producto> ordenarProductosPorNombreDeFormaAscendente() {
		TreeSet<Producto>prodOrdenado=new TreeSet<Producto>(new ordenarProdPorNombreAscendente());
		for (Producto p:productos) {
			prodOrdenado.add(p);
		}
		return prodOrdenado;
	}


}
