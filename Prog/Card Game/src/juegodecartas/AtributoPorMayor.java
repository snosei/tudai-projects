package juegodecartas;

public class AtributoPorMayor extends Atributo implements Cloneable{

	public AtributoPorMayor(String caract, double valor){
	// Constructor de Atributo por Mayor
		super(caract,valor);
	}

	public int compararValor(Atributo atributo2){
	// Metodo que comparará los valores de dos atributos y devolverá un resultado
	// dependiendo de su comparacion
	    if (valor > atributo2.getValor()){
	        return MAYOR; // Gana el atributo 1
	    }
	    else if(valor < atributo2.getValor()){
	        return MENOR; // Pierde el atributo 1
	    }
	    else return IGUALES; // Empataron los atributos
	}
	
	@Override
	protected Atributo clone() throws CloneNotSupportedException {
	// Redefinicion del metodo clon para poder clonar un atributo con sus elementos
		Atributo clon = new AtributoPorMayor(this.getCaract(),this.getValor());
		return clon;
	}
}
