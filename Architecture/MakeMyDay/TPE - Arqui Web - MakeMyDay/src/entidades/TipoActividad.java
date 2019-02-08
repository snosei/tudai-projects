package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Tipo_Actividad")
/**
 * Creará las instancias de los tipos de actividad, siendo cada tipo una generalizacion de la actividad
 * 
 * @author Gonzales Victor Juan, Rodriguez Joaquin, Nosei Santiago
 *
 */
public class TipoActividad implements Comparable<TipoActividad> {
	@Column(nullable=false)
	private String nombre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int idTipo;
	
	/**
	 * Constructor de clase por defecto
	 */
	public TipoActividad() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor de clase con parametros para instanciar los tipos de actividades
	 * @param nombre Sera el nombre que tenga el tipo de actividad
	 */
	public TipoActividad(String nombre){
		this.nombre=nombre;
	}
	
	public String getNombre(){
		return nombre;
	}
	public int getId(){
		return idTipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(int idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public String toString() {
		return "Tipo_Actividad [nombre=" + nombre + ", idTipo=" + idTipo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoActividad other = (TipoActividad) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	/**
	 * Metodo que comparara dos tipos de actividad tomando en cuenta sus nombres
	 */
	@Override
	public int compareTo(TipoActividad arg0) {
		return this.getNombre().compareTo(arg0.getNombre());
	}
		
	
}
