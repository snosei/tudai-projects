package juegodecartas;

public class BayaEspecifica extends Baya{

	private String atri;

    public BayaEspecifica(String nombre, double modificador,String atri){
    // Constructor de baya especifica
        super(nombre,modificador);
        this.atri=atri;
    }

    public void usarBaya(Atributo at){
    // Metodo redefinido que aplicará el efecto de la baya
  	  double valor = at.getValor()+ (modificador * at.getValor() / 100);
      if ( atri.equals(at.getCaract()))
          at.setValor(valor);
    }
}
