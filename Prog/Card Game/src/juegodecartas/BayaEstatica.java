package juegodecartas;

public class BayaEstatica extends Baya{

	public BayaEstatica(String nombre, double modificador){
	// Constructor de baya estatica
		super(nombre,modificador);
	}
	
	public void usarBaya(Atributo at){
	// Metodo redefinido que aplicará el efecto de la baya
		at.setValor(modificador);
	}
}
