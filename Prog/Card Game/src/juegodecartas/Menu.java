package juegodecartas;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu {
	
	private static Menu instancia;
	// Optamos para el menu, realizarlo usando el patron Singleton
	
	private Menu(){}
	
	public static Menu getInstancia(){
	// Metodo que instanciara el menu
		if (instancia == null){
			instancia = new Menu();
		}
		return instancia;
	}
	
	public static void main(String[] args) {
	// Metodo principal del programa
		Menu m = Menu.getInstancia();
		m.menuJuego();
	}
	
	public void menuJuego() {
	// Metodo que visualizara un menu y que iniciara la partida del juego
    	String menu;
    	Mazo mazo = null;
    	MochiladeBayas mochila = this.cosecharBayas();
    	do{
	    	System.out.println("Bienvenido al juego de cartas coleccionables");
	    	System.out.println("1 - Crear Mazo nuevo, crear cartas para el Mazo");
	    	System.out.println("2 - Cargar Mazo desde archivo(Por defecto se cargará de cartas.txt)");
		    System.out.println("3 - Visualizar el Mazo con las bayas");
			System.out.println("4 - Jugar");   		
	    	System.out.print("Ingresa una opcion del menu (Si ingresa cualquier cosa el mazo se cargará por defecto): ");
	    	menu = this.ObtenerNombreValido();
	        switch(menu){
		       	case "1":
		       		System.out.print("Ingrese el nombre del mazo: ");
		       		String nombreMazo = this.ObtenerNombreValido();
		       		mazo = new Mazo(nombreMazo);
		       		int cantCartas = 0;
		       		String respuesta = "si";
		       		System.out.println("Se le pedirá ingresar minimo 2 cartas");
		       		while (respuesta.equals("si")){
		       			cantCartas ++;
		       			System.out.print("Ingrese el nombre de la carta: ");
		       			String nombreCarta = this.ObtenerNombreValido();
		       			Carta c = new Carta(nombreCarta);
		       			int cantAtr = 0;
		       			String respuesta2 = "si";
		       			while (respuesta2.equals("si")){
		       				cantAtr++;
		       				System.out.print("Ingrese un atributo: ");
		       				String atr = this.ObtenerNombreValido();
		       				System.out.print("Ingrese el valor del atributo: ");
		       				int val = this.ObtenerEnteroValido();
		       				System.out.print("¿El atributo se medirá por Mayor o Menor?: ");
		       				String respuesta3 = this.ObtenerNombreValido();
		       				Atributo a;
		       				if ((respuesta3.equals("Menor")==true)||(respuesta3.equals("menor")==true)){
		       					a = new AtributoPorMenor(atr,val);
		       				}
		       				else{
		       					a = new AtributoPorMayor(atr,val);
		       				}
		       				c.addAtributo(a);
		       				if ((cantAtr > 3)&&(cantAtr < 8)){
		       					System.out.print("¿Quiere agregar otro atributo(si/no)?: ");
		       					respuesta2 = this.ObtenerNombreValido();
		       				}
		       			}
		       			if(mazo.addCarta(c) == true){
		       				System.out.println("Carta agregada exitosamente");
		       				if(cantCartas > 1){
		       					System.out.print("¿Quiere agregar otra carta?(si/no): ");
		       					respuesta = this.ObtenerNombreValido();
		       				}
		       			}
		       			else{
		       				System.out.println("La carta no se ha agregado porque tiene atributos diferentes a los que ya tiene la otra carta en el mazo");
		       			}
		       		}
		       		mazo.setBayaEnCartas(mochila);
	       			System.out.println("  ");
	       			System.out.println("El mazo fue creado con exito.");
	       			System.out.println("  ");
		      		break;
		       	case "3":
		       		if(mazo!=null){
		       			System.out.println("  ");
		       			System.out.println(mazo.toString());
		       		}
		       		else{
		       			System.out.println("  ");
		       			System.out.println("Para visualizar el mazo primero debe crearlo.");
		       			System.out.println("  ");
		       			menu="0";
		       		}
		       		break;
		       	case "4":
		       		if(mazo!=null){
		       			this.iniciarPartida(mazo);
		       		}
		       		else{
		       			System.out.println("  ");
		       			System.out.println("Para iniciar a jugar primero debe crear el mazo.");
		       			System.out.println("  ");
		       			menu="0";
		       		}
		       		break;
		      	default:
		      		mazo = new Mazo();
		      		mazo = mazo.leerCartas("cartas.txt", "Pokemon");
		      		mazo.setBayaEnCartas(mochila);
	       			System.out.println("  ");
	       			System.out.println("El mazo fue creado con exito.");
	       			System.out.println("  ");
		       		break;
		   }
    	}while(!(menu.equals("4"))); // Hasta que el usuario presione jugar con el mazo cargado
	}

	private void iniciarPartida(Mazo m){
	// Metodo que creara los jugadores e iniciara la partida
        String nombreJ1,nombreJ2;
        System.out.println("");
		System.out.print("Ingrese el nombre del Jugador 1: ");
        nombreJ1 = ObtenerNombreValido();
        System.out.print("Ingrese el nombre del Jugador 2: ");
        nombreJ2 = ObtenerNombreValido();
        Jugador j1 = new Jugador(nombreJ1);
        Jugador j2 = new Jugador(nombreJ2);
        int tamaño = (m.getCantCartas())/2;
        j1.repartirMazoAleatorio(m,tamaño);
        j2.repartirMazoAleatorio(m,tamaño);
        System.out.println("");
        System.out.println("INICIO DEL JUEGO");
        Juego juego = new Juego(j1,j2);
        Jugador ganador;
        ganador = juego.jugar();
        System.out.println("");
        if(ganador == null){
        	System.out.println("EMPATE - AMBOS JUGADORES SE HAN QUEDADO SIN CARTAS");
        }
        else{
        	System.out.println(ganador.getNombre()+" GANÓ EL JUEGO");
        }
        System.out.println("");
        System.out.println("FIN DEL JUEGO");
	}

    private String ObtenerNombreValido(){
	// Metodo que devolvera un string ingresado por el usuario
		String nombre = "";
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
			nombre = entrada.readLine();
		}
		catch (Exception exc ) {
			System.out.println("Hubo un error al ingresar el nombre");
		}
		return nombre;
    }

	private int ObtenerEnteroValido(){
	// Metodo que devolvera un numero ingresado por el usuario
		int num = 0, ciclo = 1;
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		do{
			try{
				num = new Integer(entrada.readLine());
				ciclo = 0;
			}
			catch (Exception exc){
				System.out.println("Ha ingresado incorrectamente, vuelva a ingresar");
			}
		} while (ciclo != 0);
		return num;
	}

	private MochiladeBayas cosecharBayas(){
	// Metodo que creara las bayas a ser utilizadas por el juego y las agregara a la Mochila de Bayas
		Baya berry = new BayaModificadora("Berry",10);
		Baya gold = new BayaModificadora("Gold",30);
		Baya burnt = new BayaModificadora("Burnt",-15);
		Baya bitter = new BayaModificadora("Bitter",-80);
		
		Baya zresa = new BayaEstatica("Zresa",150);
		Baya atania = new BayaEstatica("Atania",-5);
		Baya meloc = new BayaEstatica("Meloc",48);
		Baya safre = new BayaEstatica("Safre",-60);
		
		Baya perasi = new BayaEspecifica("Perasi",30,"ataque");
		Baya zanama = new BayaEspecifica("Zanama",4,"atespecial");
		Baya aranja = new BayaEspecifica("Aranja",-10,"velocidad");
		Baya caquic = new BayaEspecifica("Caquic",90,"peso");
		
		Baya ziuela = new BayaTemporal("Ziuela",20);
		Baya zidra = new BayaTemporal("Zidra",-20);
		Baya higog = new BayaTemporal("Higog",50);
		Baya wiki = new BayaTemporal("Wiki",-30);
				
		Baya ango = new BayaMultiple("Ango");
		Baya oram = new BayaModificadora("Oram",10);
		((BayaMultiple) ango).addBaya(oram);
		
		Baya guaya = new BayaMultiple("Guaya");
		Baya latano = new BayaTemporal("Latano",20);
		Baya peragu = new BayaModificadora("Peragu",-15);
		((BayaMultiple) guaya).addBaya(latano);
		((BayaMultiple) guaya).addBaya(peragu);
		
		Baya pabaya = new BayaMultiple("Pabaya");
		Baya pinia = new BayaEstatica("Pinia",20);
		Baya grana = new BayaMultiple("Grana");
		Baya algama = new BayaTemporal("Algama",-10);
		((BayaMultiple) grana).addBaya(algama);
		Baya ispero = new BayaModificadora("Ispero",25);
		((BayaMultiple) pabaya).addBaya(pinia);
		((BayaMultiple) pabaya).addBaya(grana);
		((BayaMultiple) pabaya).addBaya(ispero);
		
		MochiladeBayas mochila = new MochiladeBayas();
		mochila.addBaya(berry);
		mochila.addBaya(gold);
		mochila.addBaya(burnt);
		mochila.addBaya(bitter);
		mochila.addBaya(zresa);
		mochila.addBaya(atania);
		mochila.addBaya(meloc);
		mochila.addBaya(safre);
		mochila.addBaya(perasi);
		mochila.addBaya(zanama);
		mochila.addBaya(aranja);
		mochila.addBaya(caquic);
		mochila.addBaya(ziuela);
		mochila.addBaya(zidra);
		mochila.addBaya(higog);
		mochila.addBaya(wiki);
		mochila.addBaya(ango);
		mochila.addBaya(guaya);
		mochila.addBaya(pabaya);
		return mochila;
	}	
}
