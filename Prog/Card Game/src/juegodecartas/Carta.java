package juegodecartas;

import java.util.ArrayList;

public class Carta{
    private ArrayList<Atributo> atributos;
    private String nombre;
    private Baya baya;
    
    public Carta(String nombre){ 
    // Constructor de Carta sin atributos por parametro
        atributos = new ArrayList<Atributo>();
        this.nombre = nombre;
        baya= BayaInocua.getInstancia();
    }
    
    public Carta(){ 
    // Constructor de Carta vacio
        atributos = new ArrayList<Atributo>();
        nombre = "nombrecarta";
        baya= BayaInocua.getInstancia();
    }
    
    public Carta cargarCartaDesdeArchivo(String[] valores){ 
    // Metodo que ser� llamado por el mazo a la hora de cargar las cartas desde un archivo
        Carta c = new Carta(valores[0]);
        for(int i = 1; i < (valores.length)-1;i=i+3){
        // Recorre la linea del archivo para ir agregando los atributos
            if(valores[i].equals("+")){
            // Si encuentra un + en el archivo construirá el atributo por Mayor
                Atributo a = new AtributoPorMayor(valores[i+1],new Integer(valores[i+2]));
                c.addAtributo(a);  
            }
            else if(valores[i].equals("-")){
            // Si encuentra un - lo construira por Menor
                Atributo a = new AtributoPorMenor(valores[i+1],new Integer(valores[i+2]));
                c.addAtributo(a);  
            }
        }
        return c;
    }
    
    public String getNombre(){
    // Metodo que nos devuelve el nombre de la Carta
        return nombre;
    }

    public int getCantAtributos(){
    // Metodo que devuelve la cantidad de atributos que tiene la carta
    	return atributos.size();
    }
    
    public boolean verificarCarta(Carta carta){
    // Metodo que verificara si una carta es igual a otra pasada por parametro
        boolean respuesta = true;
        if(this.getCantAtributos() == carta.getCantAtributos()){
        // Pregunta si la cantidad de atributos es igual en ambas cartas
            int i = 0;
            while((i<atributos.size()) && (respuesta == true)){
            // Recorre los atributos para irlos comparando uno a uno
            	Atributo a1 = this.getAtributoAt(i);
            	Atributo a2 = carta.getAtributoNombre(a1.getCaract());
            	if(a2 == null){
            	// Pregunta si de los atributos de la segunda carta, alguno tiene el mismo nombre de la primera
            		return false;
            	}
            	else{
                    if(a1.mismoAtributo(a2)){
                    // Termina de comparar si los atributos son iguales
                        i++;
                    }
                    else{
                        respuesta = false;
                    }	
            	}
            }
            return respuesta;
        }
        else{
            return false;
        }
    }   
    
    public Atributo getAtributoNombre(String nombre){
    // Metodo que devuelve un atributo buscado por nombre de la lista de atributos de una carta
    	for(int i = 0;i<this.getCantAtributos();i++){
    		Atributo a = this.getAtributoAt(i);
    		if(a.getCaract().equals(nombre)){
    			return a; // Regresa el atributo encontrado
    		}
    	}
    	return null; // Si no encontro el atributo devuelve null
    }
    
    public Atributo getAtributoAt(int posAtr){
    // Metodo que devuelve un Atributo en una posicion especifica del Vector
    	return atributos.get(posAtr);
    }
    
    public void addAtributo(Atributo a){
    // Metodo que agrega un atributo al Vector
    	atributos.add(a);
    }
    
    public int enfrentar(Carta c2,String nomAtr){
    // Metodo que enfrentará 2 cartas utilizando un clon de los atributos, aplicandoles el efecto de la baya y comparandolos
    	Atributo a1;
    	Atributo a2;
    	try {
			a1 = this.getAtributoNombre(nomAtr).clone();
			a2 = c2.getAtributoNombre(nomAtr).clone();
	    	baya.usarBaya(a1);
			c2.getBaya().usarBaya(a2);
	        System.out.println("			     ATRIBUTOS + BAYAS");
	        System.out.println("                 	"+a1.getValor()+"     VS     "+a2.getValor());
	        System.out.println("-------------------------------------------------------------------------");	    		     
	    	return a1.compararValor(a2);
    	} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	return 0;  	
    }
    
    public void setBaya(Baya baya){
    // Metodo que seteara una baya en la carta
    	this.baya=baya;
    }
    
    public Baya getBaya(){
    // Metodo que retornara la baya de la carta
    	return baya;
    }
    
    public String toString(){
    // Metodo que permitira realizar una visualizacion de los atributos y la baya de la carta
    	String cadena = this.getNombre() + "";
    	if (!(this.getBaya().getNombre().equals("Inocua")))
    		cadena += "<"+ this.getBaya().getNombre() + "> ";
    	cadena += ": {" ;
    	Atributo a1;
    	try {
    		for (int i=0; i<this.getCantAtributos();i++){
    			a1 = this.getAtributoAt(i).clone();
    			baya.usarBaya(a1);
    			cadena +=  "["+a1.getCaract()+" : "+a1.getValor()+"] ";
    			baya.reset();
    		}	
    	}
    	catch (CloneNotSupportedException e) {
    		e.printStackTrace();
    	}	
    	cadena +="} ";
    	return cadena;
    }
}