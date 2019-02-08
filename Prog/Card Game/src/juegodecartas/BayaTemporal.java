package juegodecartas;

public class BayaTemporal extends Baya{

	final static int MAX = 3; // Constante que contendrá el maximo de usos antes de que se acabe el efecto
	private int contador; // Contador de usos de la baya

	public BayaTemporal(String nombre, double modificador){
	// Constructor de la baya temporal
		super(nombre,modificador);
		contador = 0;
	}

    public void usarBaya(Atributo at){
	// Metodo redefinido que aplicará el efecto de la baya
      contador++;
  	  double valor = at.getValor()+ ((modificador/contador) * at.getValor() / 100);
      if (!(contador>=MAX)){
    	   at.setValor(valor);
      }
    }
   
    public void reset(){
    // Metodo que seteara el contador en 0
	   contador = 0;
    }
}
