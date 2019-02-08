package persistencia;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.HistorialUsuario;

public class HistorialUsuarioDAO extends BaseJPADAO<HistorialUsuario>{

	private static HistorialUsuarioDAO hist;

	private HistorialUsuarioDAO() {
	super(HistorialUsuario.class);
	}
	
	public static HistorialUsuarioDAO getInstance(){
		if(hist==null)
			hist=new HistorialUsuarioDAO();
		return hist;
	}

	public ArrayList<HistorialUsuario> buscarEntreFechas(String userName, GregorianCalendar fromDate, GregorianCalendar toDate) {
		ArrayList<HistorialUsuario> resultados = new ArrayList<HistorialUsuario>();
		EntityManager em = EntityManagerFactory.createEntityManager();
		try{
			String usuario = userName;
			Calendar fecha1 = fromDate;
			Calendar fecha2 = toDate;
			String jpql = "SELECT h FROM HistorialUsuario h WHERE h.usuario.nombre = ?1 AND (h.actividad.fecha_realizada > ?2 AND h.fecha_fin < ?3)"; 
			Query query = em.createQuery(jpql).setParameter(1, usuario).setParameter(2,fecha1).setParameter(3,fecha2); 
			resultados.addAll(query.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (em != null){
			em.close();
		}	
		return resultados;
	}
}
