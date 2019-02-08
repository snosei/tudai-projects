package juegodecartas;
import java.util.ArrayList;

public class MochiladeBayas {
	
	private ArrayList<Baya> bayas;
	// Coleccion que contendra las bayas a ser utilizadas en el juego
	
	public MochiladeBayas(){
	// Constructor de la mochila
		bayas = new ArrayList <Baya>();
	}
	
	public void addBaya(Baya b){
	// Metodo que agregara una baya a la coleccion
		bayas.add(b);
	}
	
	public Baya darBaya(){
	// Metodo que tomara una baya aleatoria de la coleccion y la devolvera
		int postAleatorio = ((int) (Math.random() * (bayas.size())));
		Baya bayaRetorno = bayas.get(postAleatorio);
		bayas.remove(postAleatorio);
		return bayaRetorno;	
	}
	
	public int getCantBaya(){
	// Metodo que devolvera la cantidad de bayas dentro de la coleccion
		return bayas.size();
	}
	
}
