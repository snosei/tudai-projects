package juegodecartas;
import java.util.ArrayList;
import java.util.Iterator;

public class BayaMultiple extends Baya{
	
	private ArrayList<Baya> bayas;
	// Coleccion que contendra las demas bayas a ser utilizadas a la hora de aplicar el efecto
	
    public BayaMultiple(String nombre){
    // Constructor de baya multiple
        super(nombre);
        bayas = new ArrayList<Baya>();
    }
    
    public void addBaya(Baya b){
    // Metodo que agregara una baya a la coleccion
    	bayas.add(b);
    }
    
    public void usarBaya(Atributo at){
    // Metodo redefinido que aplicará el efecto de la baya
    	Iterator<Baya> i = bayas.iterator();
    	while (i.hasNext()){
    		i.next().usarBaya(at);
    	}			
    }
    
    public void reset(){
    // Metodo que reseteara todas las bayas. Para que encuentre una baya temporal dentro de la multiple
    	Iterator<Baya> i = bayas.iterator();
    	while (i.hasNext()){
    		i.next().reset();
    	}	
    }
}
