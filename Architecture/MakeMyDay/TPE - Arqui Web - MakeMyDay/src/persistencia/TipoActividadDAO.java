package persistencia;

import entidades.TipoActividad;

public class TipoActividadDAO extends BaseJPADAO<TipoActividad>{

	private static TipoActividadDAO hist;

	private TipoActividadDAO() {
	super(TipoActividad.class);
	}
	
	public static TipoActividadDAO getInstance(){
		if(hist==null)
			hist=new TipoActividadDAO();
		return hist;
	}
}
