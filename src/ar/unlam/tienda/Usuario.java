package ar.unlam.tienda;
import java.util.HashMap;
import java.util.Objects;

public class Usuario {
	private Integer posicionDeProducto = 1;
	private Integer idUsuario;
	private String correo;
	private String nombreUsuario;
	private HashMap<Integer, ProductoYCantidad> carrito;
	
	public Usuario(Integer idUser,String email, String username) {
		this.idUsuario = idUser;
		this.correo = email;
		this.nombreUsuario = username;
		this.carrito = new HashMap<Integer, ProductoYCantidad>();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(correo, idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(idUsuario, other.idUsuario);
	}

	public void agregarPruductoAlCarrito(Producto producto,Integer cantidad) {
		ProductoYCantidad pc = new ProductoYCantidad(producto, cantidad);
		this.carrito.put(posicionDeProducto,pc);
		posicionDeProducto++;
	}

	public HashMap<Integer, ProductoYCantidad> getCarrito() {
		return carrito;
	}

	public void quitarProductoDelCarrito(Integer posicion) {
		if (this.carrito.containsKey(posicion)) {
			this.carrito.remove(posicion);
		}
	}

	public void vaciarCarrito() {
		carrito.clear();
	}
	
}
