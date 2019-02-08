package entidades;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import jdk.nashorn.internal.ir.annotations.Ignore;

@Entity
@Table(name = "Usuario")
/**
 * Creará las instancias de los usuarios con la informacion personal de los mismos
 * 
 * @author Gonzales Victor Juan, Rodriguez Joaquin, Nosei Santiago
 *
 */
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_usuario;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false)
	private int DNI;
	@Column(nullable = false)
	private GregorianCalendar fechaNacimiento;
	@Column(nullable = false)
	private String password;
	@Transient
	private boolean esValido;
	/**
	 * Constructor de clase por defecto
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor de clase con parametros para instanciar los usuarios
	 * @param nombre
	 * @param apellido
	 * @param dNI
	 * @param fechaNacimiento
	 * @param password
	 */
	public Usuario(String nombre, String apellido, int dNI, GregorianCalendar fechaNacimiento, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dNI;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
		this.esValido= false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento; 
	}

	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI
				+ ", fechaNacimiento=" + formatDate(fechaNacimiento)  + ", password=" + password + "]";
	}
	public int getId(){
		return this.id_usuario;
	}
	public void setId(int id){
		this.id_usuario = id;
	}
	private String formatDate(GregorianCalendar fechaNacimiento2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setCalendar(fechaNacimiento2);
		return sdf.format(fechaNacimiento2.getTime());
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
		if (DNI != other.DNI)
			return false;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public void setEsValido(boolean b) {
		esValido = b;
		
	}	
	public boolean esValido(){
		return esValido;
	}
}
