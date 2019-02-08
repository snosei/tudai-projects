package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entidades.Actividad;
import entidades.ActividadRealizada;
import entidades.HistorialUsuario;
import entidades.TipoActividad;
import entidades.Usuario;
import utils.ConsultasUtil;

public class TestConsultasDB {
	static ConsultasUtil s;
	static 	ArrayList<Usuario> usuarios;
	static 	ArrayList<Actividad> actividades;
	static 	ArrayList<Double> estadisticas;
	static 	ArrayList<ActividadRealizada> actividadesEntreFechas;
	static 	double min;
	static 	double max;
	static 	double avg;
	
	
	static 	Usuario joa;
	static 	Usuario san;
	static 	Usuario vic;
	static 	Usuario pe;
	static 	Usuario tar;
	static 	Usuario ku;
	static 	Usuario yo;
	static 	Usuario dar;
	static	Usuario juan;
	static 	Usuario cue;
	
	static 	TipoActividad outdoor;
	static	TipoActividad indoor;
	static 	TipoActividad academico;
	static 	TipoActividad deporte;
	static 	TipoActividad ocio;
	
	static 	Actividad a1;
	static 	Actividad a2;
	static 	Actividad a3;
	static 	Actividad a4;
	static 	Actividad a5;
	static 	Actividad a6;
	static 	Actividad a7;
	static 	Actividad a8;
	static 	Actividad a9;
	static 	Actividad a10;
	
	static 	ActividadRealizada ar1;
	static 	ActividadRealizada ar2;
	static 	ActividadRealizada ar3;
	static 	ActividadRealizada ar4;
	static 	ActividadRealizada ar5;
	static 	ActividadRealizada ar6;
	static 	ActividadRealizada ar7;
	static 	ActividadRealizada ar8;
	static 	ActividadRealizada ar9;
	static 	ActividadRealizada ar10;
	static 	ActividadRealizada ar11;
	static 	ActividadRealizada ar12;
	static 	ActividadRealizada ar13;
	static 	ActividadRealizada ar14;
	static 	ActividadRealizada ar15;
	static 	ActividadRealizada ar16;
	static 	ActividadRealizada ar17;
	static 	ActividadRealizada ar18;
	static 	ActividadRealizada ar19;
	static 	ActividadRealizada ar20;
	
	static 	HistorialUsuario h1;
	static 	HistorialUsuario h2;
	static 	HistorialUsuario h3;
	static 	HistorialUsuario h4;
	static 	HistorialUsuario h5;
	static 	HistorialUsuario h6;
	static 	HistorialUsuario h7;
	static 	HistorialUsuario h8;
	static 	HistorialUsuario h9;
	static 	HistorialUsuario h10;
	static	HistorialUsuario h11;
	static 	HistorialUsuario h12;
	static 	HistorialUsuario h13;
	static 	HistorialUsuario h14;
	static	HistorialUsuario h15;
	static 	HistorialUsuario h16;
	static	HistorialUsuario h17;
	static	HistorialUsuario h18;
	static	HistorialUsuario h19;
	static	HistorialUsuario h20;


	@BeforeClass
	public static void inicializacion(){
				s = ConsultasUtil.getInstance();
		
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
				usuarios = new ArrayList<Usuario>();
				usuarios.add(joa);
				usuarios.add(san);
				usuarios.add(vic);
				usuarios.add(pe);
				usuarios.add(tar);
				usuarios.add(ku);
				usuarios.add(yo);
				usuarios.add(dar);
				usuarios.add(juan);
				usuarios.add(cue);
				//para los distintos tests
				
				//tipos
				outdoor = new TipoActividad("outdoor");
				indoor = new TipoActividad("indoor");
				academico = new TipoActividad("academico");
				deporte = new TipoActividad("deporte");
				ocio = new TipoActividad("ocio");

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
				
				actividades = new ArrayList<Actividad>();
				actividades.add(a1);
				actividades.add(a2);
				actividades.add(a3);
				actividades.add(a4);
				actividades.add(a5);
				actividades.add(a6);
				actividades.add(a7);
				actividades.add(a8);
				actividades.add(a9);
				actividades.add(a10);
				


				//actividades realizadas
				ar1 = new ActividadRealizada(a1, new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar2 = new ActividadRealizada(a1, new GregorianCalendar(2016,5,23,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar3 = new ActividadRealizada(a2, new GregorianCalendar(2016,6,15,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar4 = new ActividadRealizada(a2, new GregorianCalendar(2016,5,20,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar5 = new ActividadRealizada(a3, new GregorianCalendar(2016,7,23,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar6 = new ActividadRealizada(a3, new GregorianCalendar(2016,1,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar7 = new ActividadRealizada(a4, new GregorianCalendar(2016,9,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar8 = new ActividadRealizada(a4, new GregorianCalendar(2016,10,13,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar9 = new ActividadRealizada(a5, new GregorianCalendar(2016,4,30,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar10 = new ActividadRealizada(a5, new GregorianCalendar(2016,8,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar11 = new ActividadRealizada(a6, new GregorianCalendar(2016,3,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar12 = new ActividadRealizada(a6, new GregorianCalendar(2016,9,4,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar13 = new ActividadRealizada(a7, new GregorianCalendar(2016,9,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar14 = new ActividadRealizada(a7, new GregorianCalendar(2016,2,3,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar15 = new ActividadRealizada(a8, new GregorianCalendar(2016,7,31,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar16 = new ActividadRealizada(a8, new GregorianCalendar(2016,2,4,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar17 = new ActividadRealizada(a9, new GregorianCalendar(2016,11,19,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar18 = new ActividadRealizada(a9, new GregorianCalendar(2016,0,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar19 = new ActividadRealizada(a10, new GregorianCalendar(2016,2,28,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				ar20 = new ActividadRealizada(a10, new GregorianCalendar(2016,6,24,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));

				//historiales de usuario
				h1 = new HistorialUsuario(tar, ar1);
				h1.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h1.setNivelFelicidad(4);
				h2 = new HistorialUsuario(tar, ar2);
				h2.setFecha_fin(new GregorianCalendar(2016,1,6,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h2.setNivelFelicidad(3);
				h3 = new HistorialUsuario(tar, ar3);
				h3.setFecha_fin(new GregorianCalendar(2016,8,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h3.setNivelFelicidad(3);
				h4 = new HistorialUsuario(tar, ar4);
				h4.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h4.setNivelFelicidad(4);
				h5 = new HistorialUsuario(tar, ar5);
				h5.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h5.setNivelFelicidad(5);
				h6 = new HistorialUsuario(tar, ar6);
				h6.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h6.setNivelFelicidad(1);
				h7 = new HistorialUsuario(tar, ar7);
				h7.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h7.setNivelFelicidad(2);
				h8 = new HistorialUsuario(tar, ar8);
				h8.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h8.setNivelFelicidad(3);
				h9 = new HistorialUsuario(tar, ar9);
				h9.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h9.setNivelFelicidad(4);
				h10 = new HistorialUsuario(tar, ar10);
				h10.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h10.setNivelFelicidad(5);
				h11 = new HistorialUsuario(tar, ar11);
				h11.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h11.setNivelFelicidad(1);
				h12 = new HistorialUsuario(tar, ar12);
				h12.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h12.setNivelFelicidad(2);
				h13 = new HistorialUsuario(tar, ar13);
				h13.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h13.setNivelFelicidad(3);
				h14 = new HistorialUsuario(tar, ar14);
				h14.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h14.setNivelFelicidad(4);
				h15 = new HistorialUsuario(tar, ar15);
				h15.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h15.setNivelFelicidad(5);
				h16 = new HistorialUsuario(tar, ar16);
				h16.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h16.setNivelFelicidad(1);
				h17 = new HistorialUsuario(tar, ar17);
				h17.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h17.setNivelFelicidad(2);
				h18 = new HistorialUsuario(tar, ar18);
				h18.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h18.setNivelFelicidad(3);
				h19 = new HistorialUsuario(tar, ar19);
				h19.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h19.setNivelFelicidad(4);
				h20 = new HistorialUsuario(tar, ar20);
				h20.setFecha_fin(new GregorianCalendar(2016,7,2,Calendar.HOUR,Calendar.MINUTE,Calendar.SECOND));
				h20.setNivelFelicidad(5);
				
				min = 1;
				max = 5;
				avg = 3.2;
				
				actividadesEntreFechas = new ArrayList<ActividadRealizada>();
				actividadesEntreFechas.add(ar2);
		}
	
	@Test
	public void listarUsuarios(){
		assertEquals(usuarios, s.listarUsuarios());
	}
	
	@Test
	public void listarActividades(){
		assertEquals(actividades, s.listarActividades());
	}
	
	@Test
	public void listarActividadesEntreFechas(){
		assertEquals(actividadesEntreFechas, s.listarActividadesEntreFechas());
	}
	
	@Test
	public void obtenerEstadisticas(){
		ArrayList<Object[]> resultados = s.obtenerEstadisticas();
		assertEquals((Double)resultados.get(0)[0], avg,1e-6);
		assertEquals((Double)resultados.get(0)[1], min,1e-6);
		assertEquals((Double)resultados.get(0)[2], max,1e-6);
	}
	
	@AfterClass
	public static void cerrarFactoryYEliminarTablas(){
		s.eliminarDatosDB();
		s.cerrarFactory();
	}

}
