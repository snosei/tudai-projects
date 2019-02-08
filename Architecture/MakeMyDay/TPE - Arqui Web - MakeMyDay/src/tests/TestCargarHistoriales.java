package tests;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entidades.ActividadRealizada;
import entidades.HistorialUsuario;
import entidades.Usuario;

public class TestCargarHistoriales {
	
	private static EntityManagerFactory emfactory;
	private static EntityManager emanager;
	
	static Usuario tar;

	static HistorialUsuario h1;
	static HistorialUsuario h2;
	static HistorialUsuario h3;
	static HistorialUsuario h4;
	static HistorialUsuario h5;
	static HistorialUsuario h6;
	static HistorialUsuario h7;
	static HistorialUsuario h8;
	static HistorialUsuario h9;
	static HistorialUsuario h10;
	static HistorialUsuario h11;
	static HistorialUsuario h12;
	static HistorialUsuario h13;
	static HistorialUsuario h14;
	static HistorialUsuario h15;
	static HistorialUsuario h16;
	static HistorialUsuario h17;
	static HistorialUsuario h18;
	static HistorialUsuario h19;
	static HistorialUsuario h20;

	
	@Before
	public void Before(){
		emanager = emfactory.createEntityManager();
	}
	@After
	public void After(){
		if (emanager != null){
			emanager.close();
		}	
	}

	@BeforeClass
	public static void inicializacion(){
		emfactory = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
		emanager = emfactory.createEntityManager();				
		//usuarios
		String jpql = "SELECT u FROM Usuario u WHERE u.nombre = ?1"; 
		Query query = emanager.createQuery(jpql).setParameter(1, "tar"); 
		List<Usuario> resultados = query.getResultList();
		tar = resultados.get(0);
		
		List<ActividadRealizada> actividadesRelizadas = emanager.createQuery("SELECT ar FROM ActividadRealizada ar").getResultList();

		//historiales de usuario
		h1 = new HistorialUsuario(tar, actividadesRelizadas.get(0));
		h1.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h1.setNivelFelicidad(4);
		h2 = new HistorialUsuario(tar, actividadesRelizadas.get(1));
		h2.setFecha_fin(new GregorianCalendar(2016,1,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h2.setNivelFelicidad(3);
		h3 = new HistorialUsuario(tar, actividadesRelizadas.get(2));
		h3.setFecha_fin(new GregorianCalendar(2016,8,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h3.setNivelFelicidad(3);
		h4 = new HistorialUsuario(tar, actividadesRelizadas.get(3));
		h4.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h4.setNivelFelicidad(4);
		h5 = new HistorialUsuario(tar, actividadesRelizadas.get(4));
		h5.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h5.setNivelFelicidad(5);
		h6 = new HistorialUsuario(tar, actividadesRelizadas.get(5));
		h6.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h6.setNivelFelicidad(1);
		h7 = new HistorialUsuario(tar, actividadesRelizadas.get(6));
		h7.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h7.setNivelFelicidad(2);
		h8 = new HistorialUsuario(tar, actividadesRelizadas.get(7));
		h8.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h8.setNivelFelicidad(3);
		h9 = new HistorialUsuario(tar, actividadesRelizadas.get(8));
		h9.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h9.setNivelFelicidad(4);
		h10 = new HistorialUsuario(tar, actividadesRelizadas.get(9));
		h10.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h10.setNivelFelicidad(5);
		h11 = new HistorialUsuario(tar, actividadesRelizadas.get(10));
		h11.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h11.setNivelFelicidad(1);
		h12 = new HistorialUsuario(tar, actividadesRelizadas.get(11));
		h12.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h12.setNivelFelicidad(2);
		h13 = new HistorialUsuario(tar, actividadesRelizadas.get(12));
		h13.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h13.setNivelFelicidad(3);
		h14 = new HistorialUsuario(tar, actividadesRelizadas.get(13));
		h14.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h14.setNivelFelicidad(4);
		h15 = new HistorialUsuario(tar, actividadesRelizadas.get(14));
		h15.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h15.setNivelFelicidad(5);
		h16 = new HistorialUsuario(tar, actividadesRelizadas.get(15));
		h16.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h16.setNivelFelicidad(1);
		h17 = new HistorialUsuario(tar, actividadesRelizadas.get(16));
		h17.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h17.setNivelFelicidad(2);
		h18 = new HistorialUsuario(tar, actividadesRelizadas.get(17));
		h18.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h18.setNivelFelicidad(3);
		h19 = new HistorialUsuario(tar, actividadesRelizadas.get(18));
		h19.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h19.setNivelFelicidad(4);
		h20 = new HistorialUsuario(tar, actividadesRelizadas.get(19));
		h20.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
		h20.setNivelFelicidad(5);

	}
		
	@Test
	public void testCargarHistoriales() {
		emanager.getTransaction().begin();
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
	}	
	
	@AfterClass
	public static void cerrarFactory(){
		if (emfactory != null){
			emfactory.close();
		}	
	}

}
