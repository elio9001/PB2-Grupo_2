package ar.unlam.tienda;

import java.util.Objects;

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


	@Override
	public int hashCode() {
		return Objects.hash(correo);
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
		return Objects.equals(correo, other.correo);
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}
	

}
