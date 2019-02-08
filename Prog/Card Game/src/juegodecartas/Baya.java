package juegodecartas;

public abstract class Baya {

	private String nombre;
	protected double modificador;

	public Baya(String nombre){
	// Constructor de baya con nombre pero sin modificador
		this.nombre=nombre;
		this.modificador=0;
    }

	public Baya(String nombre,double modificador){
	// Constructor de baya normal, con nombre y con modificador
		this.nombre=nombre;
		this.modificador=modificador;
    }

	public String getNombre(){
	// Get del nombre de una baya
		return nombre;
	}

	public double getMod(){
	// Get del valor del modificador de una baya
		return modificador;
	}

	public abstract void usarBaya(Atributo at);
	// Metodo abstracto que definira el uso de la baya

	public void reset(){}
	// Metodo que unicamente se redefinira dentro de la baya temporal
}
