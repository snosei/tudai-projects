package tests;

import java.util.ArrayList;
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

import entidades.Actividad;
import entidades.TipoActividad;

public class TestCargarActividades {

	private static EntityManagerFactory emfactory;
	private static EntityManager emanager;
	
	static TipoActividad outdoor;
	static TipoActividad indoor;
	static TipoActividad academico;
	static TipoActividad deporte;
	static TipoActividad ocio;
	
	static Actividad a1;
	static Actividad a2;
	static Actividad a3;
	static Actividad a4;
	static Actividad a5;
	static Actividad a6;
	static Actividad a7;
	static Actividad a8;
	static Actividad a9;
	static Actividad a10;
	
	
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
	public static void inicializacion() {
		emfactory = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
		emanager = emfactory.createEntityManager();
		String jpql = "SELECT t FROM TipoActividad t"; 
		Query query = emanager.createQuery(jpql); 
		List<TipoActividad> resultados = query.getResultList();
		
		outdoor = resultados.get(0);
		indoor = resultados.get(1);
		academico = resultados.get(2);
		deporte = resultados.get(3);
		ocio = resultados.get(4);
		
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
		a1 = new Actividad("Cazar Pokemon", ta1);
		a2 = new Actividad("Comer Helado", ta1);
		a3 = new Actividad("Jugar al futbol", ta2);
		a4 = new Actividad("Trabajar", ta3);
		a5 = new Actividad("Ir a cursar", ta4);
		a6 = new Actividad("Comer facturas", ta5);
		a7 = new Actividad("Hacer el tpe de arqui", ta4);
		a8 = new Actividad("Evoluciona pokemones", ta5);
		a9 = new Actividad("Ir al GYM", ta5);
		a10 = new Actividad("Correr", ta2);
	}
	
	@Test
	public void testCargarActividades() {
		emanager.getTransaction().begin();
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
		emanager.getTransaction().commit();
	}
	
	@AfterClass
	public static void cerrarFactory(){
		if (emfactory != null){
			emfactory.close();
		}	
	}


}
