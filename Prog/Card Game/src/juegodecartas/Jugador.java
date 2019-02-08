package juegodecartas;

public class Jugador {
    private Mazo mazo;
    private String nombre;

    public Jugador(String nombre, Mazo mazo){
    // Constructor de la clase Jugador con nombre y un mazo por parametros
        this.mazo = mazo;
        this.nombre = nombre;
    }

    public Jugador(String nombre){
    // Constructor de la clase Jugador solo con nombe y un mazo vacio
    	mazo = new Mazo();
    	this.nombre = nombre;
    }

    public String getNombre(){
    // Metodo que devuelve un nombre de Jugador
        return nombre;
    }

    public String elegirAtributo(Carta c){
    // Metodo que elegira mediante un aleatorio el atributo con el que se enfrentaran las cartas
        int cant = c.getCantAtributos();
        int posAtr = ((int) (Math.random() * (cant)));
        return c.getAtributoAt(posAtr).getCaract();
    }

    public void repartirMazoAleatorio(Mazo m, int tamaño){
    // Metodo que le insertara al mazo de un jugador las cartas del mazo total de forma aleatoria
    	int aleatorio;
        for (int i = 0; i < tamaño; i++){
        	aleatorio = ((int) (Math.random() * (m.getCantCartas())));
        	mazo.addCarta(m.getCartaAt(aleatorio));
        	m.removeCarta(aleatorio);
        }
    }

    public void pasarCartas(Mazo m){
    // Metodo que pasara las cartas de un mazo al jugador
    	for (int i=0; i< m.getCantCartas(); i++){
    		mazo.addCarta(m.getCartaAt(i));
    	}
    }

    public boolean mazoEstaVacio(){
    // Metodo que devolvera 0 si el mazo esta vacio y 1 en caso contrario
    	if(mazo.getCantCartas() == 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    public int getCantCartasMazo(){
    // Metodo que devolvera la cantidad de cartas del mazo del jugador
    	return mazo.getCantCartas();
    }

    public Carta jugarCartaEnMesa(int pos){
    // Mezo que devolvera una carta especifica por posicion, del mazo del jugador
    	Carta c = mazo.getCartaAt(pos);
    	mazo.removeCarta(pos);
    	return c;
    }
    
}
