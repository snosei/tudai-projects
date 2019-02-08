package juegodecartas;

public class AtributoPorMenor extends Atributo implements Cloneable{

	public AtributoPorMenor(String caract, double valor){
	// Constructor de Atributo por Menor
		super(caract,valor);
	}

	public int compararValor(Atributo atributo2){
	// Metodo que comparará los valores de dos atributos y devolverá un resultado
	// dependiendo de su comparacion
		if(valor < atributo2.getValor()){
	        return MAYOR; // Gana el atributo 1
	    }
	    else if(valor > atributo2.getValor()){
	        return MENOR; // Pierde el atributo 2
	    }
	    else return IGUALES; // Empataron los atributos
	}
	
	@Override
	protected Atributo clone() throws CloneNotSupportedException {
	// Redefinicion del metodo clon para poder clonar un atributo con sus elementos
		Atributo clon = new AtributoPorMenor(this.getCaract(),this.getValor());
		return clon;
	}

}
