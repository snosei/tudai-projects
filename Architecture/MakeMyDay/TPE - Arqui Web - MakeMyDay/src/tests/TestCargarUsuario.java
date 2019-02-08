package tests;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entidades.Usuario;

public class TestCargarUsuario {

	private static EntityManagerFactory emfactory;
	private EntityManager emanager;
	
	static Usuario joa;
	static Usuario san;
	static Usuario vic;
	static Usuario pe;
	static Usuario tar;
	static Usuario ku;
	static Usuario yo;
	static Usuario dar;
	static Usuario juan;
	static Usuario cue;
	

	@BeforeClass
	public static void inicializacion(){
		emfactory = Persistence.createEntityManagerFactory("TPE-MakeMyDay-JPA");
				
		//usuarios
		joa = new Usuario("Joa","Quin",35647897,new GregorianCalendar(1994,7,25) ,"2567");
		san = new Usuario("San","Tiago",38838109,new GregorianCalendar(1995,7,25) ,"2567");
		vic = new Usuario("Vic","Tor",58938019,new GregorianCalendar(1985,7,25) ,"2567");
		pe = new Usuario("Pe","Tuto",38698719,new GregorianCalendar(1955,7,25) ,"2567");
		tar = new Usuario("Tar","ton",3,new GregorianCalendar(1095,7,25) ,"2567");
		ku = new Usuario("Ku","Be",35585462,new GregorianCalendar(1995,9,5) ,"2567");
		yo = new Usuario("Yo","Landa",49836019,new GregorianCalendar(1895,7,25) ,"2567");
		dar = new Usuario("Dar","do",38999019,new GregorianCalendar(1999,7,25) ,"2567");
		juan = new Usuario("Juan","Se",38838019,new GregorianCalendar(1998,7,25) ,"2567");
		cue = new Usuario("Cue","Li",39874519,new GregorianCalendar(1997,7,25) ,"2567");
		
	}		
	
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

	@Test
	public void testCargarUsuarios() {
		emanager.getTransaction().begin();
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
		emanager.getTransaction().commit();
	}	
	
	@AfterClass
	public static void cerrarFactory(){
		if (emfactory != null){
			emfactory.close();
		}	
	}

}
