package juegodecartas;

public abstract class Atributo implements Cloneable{
	protected String caract;
	protected double valor;

	// Constantes que serún devueltas luego
	final static int MAYOR = 1;
	final static int MENOR = -1;
	final static int IGUALES = 0;

	public Atributo(String caract, double valor){
	// Constructor de la clase Atributo
		this.caract = caract;
		this.valor = valor;
	}

	public String getCaract(){
	// Metodo que devuelve el nombre de la caracteristica del atributo
		return caract;
	}

	public double getValor(){
	// Metodo que devuelve el valor del atributo
		return valor;
	}

	public boolean mismoAtributo(Atributo atributo2){
	// Metodo que compara si un atributo es igual a otro pasado por parametro
		if(this.getClass().getName().equals(atributo2.getClass().getName())){
		// Pregunta si los atributos se comparan de la misma forma
			if(this.getCaract().equals(((Atributo) atributo2).getCaract())){
			// Pregunta si los nombres de las caracteristicas son iguales
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	public abstract int compararValor(Atributo atributo2); // Metodo Abstracto

	@Override
	protected abstract Atributo clone() throws CloneNotSupportedException;
	// Metodo abstracto de la interfaz Cloneable que sera redefinido por los otros atributos

	public void setValor(double valor){
	// Metodo que seteara el valor de un atributo
		this.valor=valor;
	}
}
