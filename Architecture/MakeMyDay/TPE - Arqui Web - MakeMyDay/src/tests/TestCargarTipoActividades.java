package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entidades.TipoActividad;

public class TestCargarTipoActividades {

	private static EntityManagerFactory emfactory;
	private EntityManager emanager;
	
	static TipoActividad outdoor;
	static TipoActividad indoor;
	static TipoActividad academico;
	static TipoActividad deporte;
	static TipoActividad ocio;

	
	
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
	public static void init(){
		emfactory = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
		outdoor = new TipoActividad("outdoor");
		indoor = new TipoActividad("indoor");
		academico = new TipoActividad("academico");
		deporte = new TipoActividad("deporte");
		ocio = new TipoActividad("ocio");
	}
	
	
	@Test
	public void test() {
		emanager.getTransaction().begin();
		emanager.persist(outdoor);
		emanager.persist(indoor);
		emanager.persist(academico);
		emanager.persist(deporte);
		emanager.persist(ocio);
		emanager.getTransaction().commit();
	}
	
	
	@AfterClass
	public static void cerrarFactory(){
		if (emfactory != null){
			emfactory.close();
		}	
	}

}
