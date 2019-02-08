package juegodecartas;

public class BayaModificadora extends Baya{

	public BayaModificadora(String nombre, double modificador){
	// Constructor de la baya modificadora
	    super(nombre,modificador);
	}
	
    public void usarBaya(Atributo at){
	// Metodo redefinido que aplicará el efecto de la baya
    	double valor = at.getValor()+ (modificador * at.getValor() / 100);
    	at.setValor(valor);
    }
}
