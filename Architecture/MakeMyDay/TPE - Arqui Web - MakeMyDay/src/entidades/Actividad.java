package entidades;


import java.util.ArrayList;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table; 


@Entity
@Table(name = "Actividad")
/**
 * Creara las instancias de las actividades que podra realizar un usuario con su informacion correspondiente
 * @author Gonzales Victor Juan, Rodriguez Joaquin, Nosei Santiago
 *
 */
public class Actividad {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private int idTipo;
	
	@Column(nullable=false)
	private String nombre;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn
	private ArrayList<TipoActividad> ListadeTipo;

	/**
	 * Constructor de clase por defecto
	 */
	public Actividad() {
		ListadeTipo = new ArrayList<TipoActividad>();
	}

	/**
	 * Constructor de clase con parametros para instanciar las actividades
	 * @param nombre Sera el nombre que mantendra la actividad en el sistema
	 * @param listadeTipo Sera una lista de tipos de actividades para la actividad asociada
	 */
	public Actividad(String nombre, ArrayList<TipoActividad> listadeTipo) {
		this.nombre = nombre;
		ListadeTipo = listadeTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<TipoActividad> getListadeTipo() {
		return (ArrayList<TipoActividad>) ListadeTipo;
	}

	@Override
	public String toString() {
		return "Actividad [idTipo=" + idTipo + ", nombre=" + nombre + ", ListadeTipo=" + ListadeTipo.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actividad other = (Actividad) obj;
		Collections.sort(other.getListadeTipo());
		Collections.sort(ListadeTipo);
		if (ListadeTipo == null) {
			if (other.getListadeTipo() != null)
				return false;
		} else if (!ListadeTipo.equals(other.getListadeTipo()))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
			
	
}
