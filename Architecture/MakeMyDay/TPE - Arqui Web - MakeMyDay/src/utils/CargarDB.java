package utils;

public class CargarDB {

	public static void main(String[] args) {
		ConsultasUtil consultas = ConsultasUtil.getInstance();
		consultas.cargarDatosEnDB();
		consultas.cerrarFactory();
	}

}
