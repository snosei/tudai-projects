package juegodecartas;

public class Juego {

	// Constantes
	final static int MAYOR = 1;
	final static int MENOR = -1;
	final static int IGUALES = 0;

	private Jugador j1;
	private Jugador j2;
	private Mazo mesa;

	public Juego(Jugador j1,Jugador j2){
	// Constructor de la clase Juego con los dos jugadores
		this.j1 = j1;
		this.j2 = j2;
		mesa = new Mazo();
	}


	public Jugador jugar(){
	// Metodo que resolvera el juego y devolvera al ganador o nadie si hubo empate
		Jugador turno = j1;
		Jugador otro = j2;
		Jugador aux = null; // Jugador usado para el cambio de turno entre jugadores
		int ganador = 1; // Variable que contendra 1 si gano un jugador o 0 si hubo un empate
		int ronda = 0; // Variable que llevara la cuenta de las rondas
		String nomAtr = null; // Variable que contendra el nombre del atributo a comparar en un enfrentamiento
		int pos = 0; // Variable que contendra la posicion de las cartas dentro del mazo mesa
		while ((!(j1.mazoEstaVacio())) && (!(j2.mazoEstaVacio()))){
		// Mientras que ambos jugadores no tengan sus mazos vacios se jugara
	    	ronda++;
	    	mesa.addCarta(turno.jugarCartaEnMesa(0)); // Agrega la carta del jugador al mazo mesa para ser comparada
	    	mesa.addCarta(otro.jugarCartaEnMesa(0));
	    	if(ganador != 0){
	    	// Si no hubo empate, el jugador de turno elegira un atributo aleatorio
	    		nomAtr = turno.elegirAtributo(mesa.getCartaAt(0));
	    	}
	    	this.mostrarTrazaJuego(turno,otro,mesa,ronda,nomAtr, pos);
	        int resultado = mesa.getCartaAt(pos).enfrentar(mesa.getCartaAt(pos+1),nomAtr);
			// Variable que guardara el resultado de haber enfrentado las cartas de ambos jugadores
	        if (resultado == MAYOR){
	            System.out.println("	||||Ganó el jugador " + turno.getNombre()+" y podrá jugar nuevamente||||");
	            System.out.println("-------------------------------------------------------------------------");
	            System.out.println("-------------------------------FIN DE RONDA------------------------------");
	            turno.pasarCartas(mesa);
	            mesa.vaciarMazo();
	            pos = 0;
	            ganador = 1;
	        }
	        else if(resultado == MENOR){
	            System.out.println("	||||Ganó el Jugador " + otro.getNombre()+" y podrá jugar nuevamente||||");
	            System.out.println("-------------------------------------------------------------------------");
	            System.out.println("-------------------------------FIN DE RONDA------------------------------");
	            otro.pasarCartas(mesa);
	            mesa.vaciarMazo();
	            aux = turno;
	            turno =otro;
	            otro = aux;
				// Cambia el jugador de turno para que sea el otro
	            pos = 0;
	            ganador = -1;
	        }
	        else if(resultado == IGUALES){
	            System.out.println("|Empataron los atributos, se elegirá una nueva carta y seguirá el juego|");
	            System.out.println("-------------------------------------------------------------------------");
	            System.out.println("-------------------------------FIN DE RONDA------------------------------");
	            if((!(turno.mazoEstaVacio()))&&(!(otro.mazoEstaVacio()))){
	        	   pos += 2;
	        	   ganador = 0;
	            }
	        }
		}
		if(ganador == 1) {
		// Si hubo un ganador se devuelve ese jugador
			System.out.println("El jugador "+ otro.getNombre() + " se ha quedado sin cartas.");
			return turno;
		}
		else{
			// Si hubo empate, pregunta quien tiene un mazo vacio, y si son ambos se devuelve null para que muestre empate
			if (turno.mazoEstaVacio()){
				System.out.println("El jugador "+ turno.getNombre() + " se ha quedado sin cartas.");
				return otro;
			}
			else if (otro.mazoEstaVacio()){
				System.out.println("El jugador "+ otro.getNombre() + " se ha quedado sin cartas.");
				return turno;
			}
				else{
					System.out.println("Ambos jugadores se han quedado sin cartas");
					return null;
				}
		}
    }

	private void mostrarTrazaJuego(Jugador turno,Jugador otro,Mazo enMesa,int ronda,String nomAtr, int pos){
	// Metodo que irá mostrando una traza del juego por cada ronda
    	System.out.println("");
    	System.out.println("-----------------------------INICIO DE RONDA-----------------------------");
    	System.out.println("                		   "+ronda);
    	System.out.println("		   JUEGA "+turno.getNombre()+" Y LE SEGUIRA "+otro.getNombre());
    	System.out.println("		   CARTAS RESTANTES: "+ turno.getCantCartasMazo() + "     VS     "+ otro.getCantCartasMazo());
    	System.out.println("		   CARTAS: "+enMesa.getNombreCarta(pos)+"     VS     "+enMesa.getNombreCarta(pos+1));
        Carta c1 = enMesa.getCartaAt(pos);
        Carta c2 = enMesa.getCartaAt(pos+1);
    	Baya b1 = c1.getBaya();
    	Baya b2 = c2.getBaya();
        System.out.println("                                ATRIBUTOS");
        System.out.println("                   "+nomAtr+" = "+c1.getAtributoNombre(nomAtr).getValor()+" VS "+nomAtr+" = "+c2.getAtributoNombre(nomAtr).getValor()); 
        System.out.println("		     BAYAS: "+b1.getNombre()+"        "+b2.getNombre());
    }
}
