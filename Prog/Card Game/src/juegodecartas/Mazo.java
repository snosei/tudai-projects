package juegodecartas;

import java.io.*;
import java.util.ArrayList;

public class Mazo {
	private ArrayList<Carta> cartas;
	private String nombre;
	private BufferedReader br;

    public Mazo(String nombre){
		// Constructor de la clase Mazo con un nombre por parametro
        cartas = new ArrayList<Carta>();
        this.nombre=nombre;
    }

    public Mazo(){
		// Constructor de la clase Mazo con nombre por defecto y vector vacio
    	cartas = new ArrayList<Carta>();
    	nombre = "nombremazo";
    }

	public Mazo leerCartas(String rutaArchivo,String nombre){
	// Metodo que devolvera un mazo con las cartas leidas desde un archivo
		Mazo cartasLeidas = new Mazo(nombre);
		try{
			InputStream is = new FileInputStream(rutaArchivo);
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String read = br.readLine();
			while(read != null){
				String[] valores = read.split(",");
				Carta c = new Carta(); //crear carta con el constructor vacio 
				c = c.cargarCartaDesdeArchivo(valores);
				cartasLeidas.addCarta(c);
				read = br.readLine();
			}
			return cartasLeidas;
		}
		catch (IOException ex){
			System.out.println("Ruta de archivo mal escrita");
			return cartasLeidas;
		}
	}

	public String getNombre(){
	// Metodo que devolvera el nombre del mazo
		return nombre;
	}

	public Carta getCartaAt(int pos){
	// Metodo que devolvera una carta en una posicion dada
		return cartas.get(pos);
	}

	public int getCantCartas(){
	// Metodo que devolvera la cantidad de cartas del mazo
		return cartas.size();
	}

	public void removeCarta(int pos){
	// Metodo que eliminara una carta del mazo en una posicion
		cartas.remove(pos);
	}

	public boolean addCarta(Carta carta){
	// Metodo que agregara una carta al mazo teniendo en cuenta algunos criterios
		if (cartas.isEmpty()){
		// Si el mazo esta vacio la primera carta se agrega normalmente
			cartas.add(carta);
			return true;

		}
		else{
			if(this.getCartaAt(0).verificarCarta(carta)){
			// Si la verificacion de la segunda carta con la que ya estaba devuelve true se agregara
				cartas.add(carta);
				return true;
			}
			else{
				return false;
			}
		}
	}

	public String getNombreCarta(int pos){
	// Metodo que devolvera el nombe de una carta en una posicion dada
		return this.getCartaAt(pos).getNombre();
	}

	public void vaciarMazo(){
	// Metodo que vaciara el mazo de cartas
		cartas.clear();
	}
	
	public void setBayaEnCartas(MochiladeBayas mochila){
	// Metodo que seteara una baya aleatoria en cada carta de un mazo
		int i = 0;
		while((mochila.getCantBaya()>0)&&(i < this.getCantCartas())){
			this.getCartaAt(i).setBaya(mochila.darBaya());
			i++;
		}
	}
	
	public String toString(){
	// Metodo que permitira una visualizacion completa de las cartas de un mazo
		String cadena = "";
		for (int i=0; i<this.getCantCartas();i++) {
			cadena += this.getCartaAt(i).toString() + "\n"; 
		}
		return cadena;
	}
	
}
