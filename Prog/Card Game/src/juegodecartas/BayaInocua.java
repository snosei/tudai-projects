package juegodecartas;

public class BayaInocua extends Baya{
	// Para el caso de la baya inocua usamos el patron Singleton
	private static BayaInocua instancia;
	
	private BayaInocua(){
	// Constructor privado de baya inocua que será instanciado una sola vez
		super("Inocua");
	}
	
	public static BayaInocua getInstancia(){
	// Metodo que instanciara la baya inocua
		if (instancia==null){
			instancia = new BayaInocua();
		}
		return instancia;
	}
	
	public void usarBaya(Atributo at){}
	// Metodo redefinido que aplicará el efecto de la baya
}
