package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Actividad;
import entidades.ActividadRealizada;
import entidades.HistorialUsuario;
import entidades.TipoActividad;
import entidades.Usuario;

/**
 * Clase creada como Singleton que proveera metodos para resolver consultas y carga en la Base de Datos
 * 
 * @author Gonzales Victor Juan, Rodriguez Joaquin, Nosei Santiago
 *
 */
public class ConsultasUtil {

	private EntityManagerFactory emfactory;
	private EntityManager emanager;
	private static ConsultasUtil instanciaConsulta; 
	
	/**
	 * Constructor privado de la clase
	 */
	private ConsultasUtil(){
		emfactory = null;
		emanager = null;
		inicializarFactory();
	}
	
	/**
	 * Metodo que devolvera la instancia de la clase
	 * @return
	 */
	public static ConsultasUtil getInstance(){
		if(instanciaConsulta==null)
			instanciaConsulta = new ConsultasUtil();
		return instanciaConsulta;
	}

	/**
	 * Metodo que inicalizara el EntityManagerFactory para poder realizar futuras acciones sobre la BD
	 */
	private void inicializarFactory(){
		try {
			emfactory = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que cerrara el EntityManagerFactory
	 */
	public void cerrarFactory(){
		if (emfactory != null){
			emfactory.close();
		}	
	}
	
	/**
	 * Metodo que devolveras una lista de las actividades realizadas en un rango de fechas
	 * traida de la BD
	 * @return
	 */
	public ArrayList<ActividadRealizada> listarActividadesEntreFechas(){
		ArrayList<ActividadRealizada> resultados = new ArrayList<ActividadRealizada>();
		try{
			emanager = emfactory.createEntityManager();
			String usuario = "tar";
			Calendar fecha1 = new GregorianCalendar(2015,7,1);
			Calendar fecha2 = new GregorianCalendar(2016,4,30);
			String jpql = "SELECT h.actividad FROM HistorialUsuario h WHERE h.usuario.nombre = ?1 AND (h.actividad.fecha_realizada > ?2 AND h.fecha_fin < ?3)"; 
			Query query = emanager.createQuery(jpql).setParameter(1, usuario).setParameter(2,fecha1).setParameter(3,fecha2); 
			resultados.addAll(query.getResultList());
			System.out.println("\n --------------Actividades entre Fechas--------------");
			for(ActividadRealizada  r : resultados) { 
				System.out.println(r.toString());
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (emanager != null){
			emanager.close();
		}	
		return resultados;
	}
	
	/**
	 * Metodo que devolvera una lista de actividades traida de la BD
	 * @return
	 */
	public ArrayList<Actividad> listarActividades(){
		ArrayList<Actividad> resultados = new ArrayList<Actividad>();
		try{
			emanager = emfactory.createEntityManager();
			String jpql = "SELECT a FROM Actividad a"; 
			Query query = emanager.createQuery(jpql); 
			resultados.addAll(query.getResultList());
			System.out.println("\n --------------Actividades--------------");
			for(Actividad  r : resultados) { 
				System.out.println(r.toString());
			} 
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (emanager != null){
			emanager.close();
		}
		return resultados;
	}
	
	/**
	 * Metodo que devolvera una lista de usuarios traida de la BD
	 * @return
	 */
	public ArrayList<Usuario> listarUsuarios(){
		ArrayList<Usuario> resultados = new ArrayList<Usuario>();
		try{
			emanager = emfactory.createEntityManager();
			String jpql = "SELECT u FROM Usuario u"; 
			Query query = emanager.createQuery(jpql); 
			resultados.addAll(query.getResultList());
			System.out.println("\n --------------Usuarios--------------");
			for(Usuario  u : resultados) { 
				System.out.println(u.toString());    
			} 
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (emanager != null){
			emanager.close();
		}		
		return resultados;
	}
	
	/**
	 * Metodo que devolvera el analisis estadistico de la felicidad de un usuario traida de la BD
	 * @return
	 */
	public ArrayList<Object[]> obtenerEstadisticas(){
		ArrayList<Object[]> resultados = new ArrayList<Object[]>();
		try{
			emanager = emfactory.createEntityManager();
			String jpql = "SELECT AVG(h.nivelFelicidad), MIN(h.nivelFelicidad), MAX(h.nivelFelicidad) FROM HistorialUsuario h WHERE h.usuario.DNI LIKE ?1"; 
			Query query = emanager.createQuery(jpql).setParameter(1, 3); 
			resultados.addAll(query.getResultList());
			System.out.println("\n ----------------Estadisticas del Usuario------------------");
			System.out.println("Promedio: " + (Double) resultados.get(0)[0]);
			System.out.println("Minimo: " + (Double) resultados.get(0)[1]);
			System.out.println("Maximo: " + (Double) resultados.get(0)[2]);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if (emanager != null){
			emanager.close();
		}	
		return resultados;
	}
	
	/**
	 * Metodo usado en el main que instancia objetos y los persiste en la BD
	 */
	public void cargarDatosEnDB() {
		try {
			emanager = emfactory.createEntityManager();
			emanager.getTransaction().begin();
			//usuarios
			Usuario joa = new Usuario("Joa","Quin",35647897,new GregorianCalendar(1994,7,25) ,"2567");
			Usuario san = new Usuario("San","Tiago",38838109,new GregorianCalendar(1995,7,25) ,"2567");
			Usuario vic = new Usuario("Vic","Tor",58938019,new GregorianCalendar(1985,7,25) ,"2567");
			Usuario pe = new Usuario("Pe","Tuto",38698719,new GregorianCalendar(1955,7,25) ,"2567");
			Usuario tar = new Usuario("Tar","ton",3,new GregorianCalendar(1095,7,25) ,"2567");
			Usuario ku = new Usuario("Ku","Be",35585462,new GregorianCalendar(1995,9,5) ,"2567");
			Usuario yo = new Usuario("Yo","Landa",49836019,new GregorianCalendar(1895,7,25) ,"2567");
			Usuario dar = new Usuario("Dar","do",38999019,new GregorianCalendar(1999,7,25) ,"2567");
			Usuario juan = new Usuario("Juan","Se",38838019,new GregorianCalendar(1998,7,25) ,"2567");
			Usuario cue = new Usuario("Cue","Li",39874519,new GregorianCalendar(1997,7,25) ,"2567");

			//tipos
			TipoActividad outdoor = new TipoActividad("outdoor");
			TipoActividad indoor = new TipoActividad("indoor");
			TipoActividad academico = new TipoActividad("academico");
			TipoActividad deporte = new TipoActividad("deporte");
			TipoActividad ocio = new TipoActividad("ocio");
			
			//Tipos de Actividades
			ArrayList<TipoActividad> ta1 = new ArrayList<TipoActividad>();
			ta1.add(ocio);
			ta1.add(outdoor);
			ArrayList<TipoActividad> ta2 = new ArrayList<TipoActividad>();
			ta2.add(ocio);
			ta2.add(outdoor);
			ta2.add(deporte);
			ArrayList<TipoActividad> ta3 = new ArrayList<TipoActividad>();
			ta3.add(indoor);
			ArrayList<TipoActividad> ta4 = new ArrayList<TipoActividad>();
			ta4.add(indoor);
			ta4.add(academico);
			ArrayList<TipoActividad> ta5 = new ArrayList<TipoActividad>();
			ta5.add(ocio);
			ta5.add(indoor);
			
			//actividades
			Actividad a1 = new Actividad("Cazar Pokemon", ta1);
			Actividad a2 = new Actividad("Comer Helado", ta1);
			Actividad a3 = new Actividad("Jugar al futbol", ta2);
			Actividad a4 = new Actividad("Trabajar", ta3);
			Actividad a5 = new Actividad("Ir a cursar", ta4);
			Actividad a6 = new Actividad("Comer facturas", ta5);
			Actividad a7 = new Actividad("Hacer el tpe de arqui", ta4);
			Actividad a8 = new Actividad("Evoluciona pokemones", ta5);
			Actividad a9 = new Actividad("Ir al GYM", ta5);
			Actividad a10 = new Actividad("Correr", ta2);

			//actividades realizadas
			ActividadRealizada ar1 = new ActividadRealizada(a1, new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar2 = new ActividadRealizada(a1, new GregorianCalendar(2016,5,23,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar3 = new ActividadRealizada(a2, new GregorianCalendar(2016,6,15,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar4 = new ActividadRealizada(a2, new GregorianCalendar(2016,5,20,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar5 = new ActividadRealizada(a3, new GregorianCalendar(2016,7,23,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar6 = new ActividadRealizada(a3, new GregorianCalendar(2016,1,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar7 = new ActividadRealizada(a4, new GregorianCalendar(2016,9,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar8 = new ActividadRealizada(a4, new GregorianCalendar(2016,10,13,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar9 = new ActividadRealizada(a5, new GregorianCalendar(2016,4,30,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar10 = new ActividadRealizada(a5, new GregorianCalendar(2016,8,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar11 = new ActividadRealizada(a6, new GregorianCalendar(2016,3,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar12 = new ActividadRealizada(a6, new GregorianCalendar(2016,9,4,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar13 = new ActividadRealizada(a7, new GregorianCalendar(2016,9,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar14 = new ActividadRealizada(a7, new GregorianCalendar(2016,2,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar15 = new ActividadRealizada(a8, new GregorianCalendar(2016,7,31,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar16 = new ActividadRealizada(a8, new GregorianCalendar(2016,2,4,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar17 = new ActividadRealizada(a9, new GregorianCalendar(2016,11,19,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar18 = new ActividadRealizada(a9, new GregorianCalendar(2016,0,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar19 = new ActividadRealizada(a10, new GregorianCalendar(2016,2,28,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			ActividadRealizada ar20 = new ActividadRealizada(a10, new GregorianCalendar(2016,6,24,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));

			//historiales de usuario
			HistorialUsuario h1 = new HistorialUsuario(tar, ar1);
			h1.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h1.setNivelFelicidad(4);

			HistorialUsuario h2 = new HistorialUsuario(tar, ar2);
			h2.setFecha_fin(new GregorianCalendar(2016,1,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h2.setNivelFelicidad(3);

			HistorialUsuario h3 = new HistorialUsuario(tar, ar3);
			h3.setFecha_fin(new GregorianCalendar(2016,8,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h3.setNivelFelicidad(3);

			HistorialUsuario h4 = new HistorialUsuario(tar, ar4);
			h4.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h4.setNivelFelicidad(4);

			HistorialUsuario h5 = new HistorialUsuario(tar, ar5);
			h5.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h5.setNivelFelicidad(5);

			HistorialUsuario h6 = new HistorialUsuario(tar, ar6);
			h6.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h6.setNivelFelicidad(1);

			HistorialUsuario h7 = new HistorialUsuario(tar, ar7);
			h7.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h7.setNivelFelicidad(2);

			HistorialUsuario h8 = new HistorialUsuario(tar, ar8);
			h8.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h8.setNivelFelicidad(3);

			HistorialUsuario h9 = new HistorialUsuario(tar, ar9);
			h9.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h9.setNivelFelicidad(4);

			HistorialUsuario h10 = new HistorialUsuario(tar, ar10);
			h10.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h10.setNivelFelicidad(5);

			HistorialUsuario h11 = new HistorialUsuario(tar, ar11);
			h11.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h11.setNivelFelicidad(1);

			HistorialUsuario h12 = new HistorialUsuario(tar, ar12);
			h12.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h12.setNivelFelicidad(2);

			HistorialUsuario h13 = new HistorialUsuario(tar, ar13);
			h13.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h13.setNivelFelicidad(3);

			HistorialUsuario h14 = new HistorialUsuario(tar, ar14);
			h14.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h14.setNivelFelicidad(4);

			HistorialUsuario h15 = new HistorialUsuario(tar, ar15);
			h15.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h15.setNivelFelicidad(5);

			HistorialUsuario h16 = new HistorialUsuario(tar, ar16);
			h16.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h16.setNivelFelicidad(1);

			HistorialUsuario h17 = new HistorialUsuario(tar, ar17);
			h17.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h17.setNivelFelicidad(2);

			HistorialUsuario h18 = new HistorialUsuario(tar, ar18);
			h18.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h18.setNivelFelicidad(3);

			HistorialUsuario h19 = new HistorialUsuario(tar, ar19);
			h19.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h19.setNivelFelicidad(4);

			HistorialUsuario h20 = new HistorialUsuario(tar, ar20);
			h20.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
			h20.setNivelFelicidad(5);


			//usuarios
			emanager.persist(joa);
			emanager.persist(san);
			emanager.persist(vic);
			emanager.persist(pe);
			emanager.persist(tar);
			emanager.persist(ku);
			emanager.persist(yo);
			emanager.persist(dar);
			emanager.persist(juan);
			emanager.persist(cue);
			//tipos de act
			emanager.persist(indoor);
			emanager.persist(academico);
			emanager.persist(ocio);
			emanager.persist(outdoor);
			emanager.persist(deporte);
			//actividades
			emanager.persist(a1);
			emanager.persist(a2);
			emanager.persist(a3);
			emanager.persist(a4);
			emanager.persist(a5);
			emanager.persist(a6);
			emanager.persist(a7);
			emanager.persist(a8);
			emanager.persist(a9);
			emanager.persist(a10);
			//actividades realizadas
			emanager.persist(ar1);
			emanager.persist(ar2);
			emanager.persist(ar3);
			emanager.persist(ar4);
			emanager.persist(ar5);
			emanager.persist(ar6);
			emanager.persist(ar7);
			emanager.persist(ar8);
			emanager.persist(ar9);
			emanager.persist(ar10);
			emanager.persist(ar11);
			emanager.persist(ar12);
			emanager.persist(ar13);
			emanager.persist(ar14);
			emanager.persist(ar15);
			emanager.persist(ar16);
			emanager.persist(ar17);
			emanager.persist(ar18);
			emanager.persist(ar19);
			emanager.persist(ar20);
			//historiales
			emanager.persist(h1);
			emanager.persist(h2);
			emanager.persist(h3);
			emanager.persist(h4);
			emanager.persist(h5);
			emanager.persist(h6);
			emanager.persist(h7);
			emanager.persist(h8);
			emanager.persist(h9);
			emanager.persist(h10);
			emanager.persist(h11);
			emanager.persist(h12);
			emanager.persist(h13);
			emanager.persist(h14);
			emanager.persist(h15);
			emanager.persist(h16);
			emanager.persist(h17);
			emanager.persist(h18);
			emanager.persist(h19);
			emanager.persist(h20);

			emanager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (emanager != null){
			emanager.close();
		}						
	}
	
	/**
	 * Metodo que elimina los datos de la BD
	 */
	public void eliminarDatosDB(){
		try{
			emanager = emfactory.createEntityManager();
			emanager.getTransaction().begin();	
		
			String jpql1 = "SELECT CONCAT('alter table ',table_name,' drop foreign key ', constraint_name,';') FROM information_schema.key_column_usage WHERE table_schema = 'makemyday' and not (constraint_name like 'primary');";
			String jpql2 = "SELECT CONCAT('drop table ',table_name,'; ') FROM information_schema.tables WHERE table_schema = 'makemyday';"; 		    
	 
			Query q1 = emanager.createNativeQuery(jpql1);
			Query q2 = emanager.createNativeQuery(jpql2); 
    
			List<String> rFK = q1.getResultList();
			List<String> rT = q2.getResultList();

			System.out.println(rFK);
			System.out.println("");
			System.out.println(rT);
		    
			for(String  r : rFK) { 
				Query qAux = emanager.createNativeQuery(r);  
				qAux.executeUpdate();
			}
			for(String  r : rT) { 
				Query qAux = emanager.createNativeQuery(r);  
		   		qAux.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (emanager != null){
			emanager.close();
		}

	}
	
}
