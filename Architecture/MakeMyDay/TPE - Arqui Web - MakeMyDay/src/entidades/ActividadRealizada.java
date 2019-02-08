package entidades;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Actividad_Realizada")
/**
 * Creará las instancias de las actividades junto con el tiempo en el que son realizadas
 * 
 * @author Gonzales Victor Juan, Rodriguez Joaquin, Nosei Santiago
 *
 */
public class ActividadRealizada {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id_actividad_realizada;
	@ManyToOne
	@JoinColumn
	private Actividad actividad;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar fecha_realizada;
	
	/**
	 * Constructor de clase por defecto
	 */
	public ActividadRealizada() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor de clase con parametros para instanciar las actividades realizadas
	 * @param actividad Sera la actividad a realizar
	 * @param fecha_realizada Sera la fecha en la que se realiza la actividad
	 */
	public ActividadRealizada(Actividad actividad,GregorianCalendar fecha_realizada){
		this.actividad = actividad;
		this.fecha_realizada = fecha_realizada;
	}

	public int getIdActividadRealizada() {
		return id_actividad_realizada;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public GregorianCalendar getFechaRealizada() {
		return fecha_realizada;
	}

	@Override
	public String toString() {
		return "Actividad_Realizada [actividad=" + actividad
				+ ", fecha_realizada=" + formatDate(fecha_realizada) + "]";
	}
	
	/**
	 * Metodo auxiliar para darle formato dd/mmm/yyy hh:ss a las fechas creadas con Calendar
	 * @param calendar Sera el calendario al que se le transforme al formato adecuado
	 * @return
	 */
	private String formatDate(GregorianCalendar calendar){
	    SimpleDateFormat fmt = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss");
	    fmt.setCalendar(calendar);
	    String dateFormatted = fmt.format(calendar.getTime());
	    return dateFormatted;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActividadRealizada other = (ActividadRealizada) obj;
		if (actividad == null) {
			if (other.actividad != null)
				return false;
		} else if (!actividad.equals(other.actividad))
			return false;
		if (fecha_realizada == null) {
			if (other.fecha_realizada != null)
				return false;
		} else if (!fecha_realizada.equals(other.fecha_realizada))
			return false;
		return true;
	}
	
}
