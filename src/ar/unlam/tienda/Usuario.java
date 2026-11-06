package ar.unlam.tienda;

public class Usuario {
	private static Integer proximoId=0;
	private Integer id;
	private String nombre;
	private String correo;
	
	
	public Usuario(String nombre, String correo) {
		this.id=++proximoId;
		this.nombre=nombre;
		this.correo=correo;
	}


	public static Integer getProximoId() {
		return proximoId;
	}


	public static void setProximoId(Integer proximoId) {
		Usuario.proximoId = proximoId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
