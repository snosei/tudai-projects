package persistencia;

import entidades.Actividad;

public class ActividadDAO extends BaseJPADAO<Actividad>{

	private static ActividadDAO daoAct;

	private ActividadDAO() {
	super(Actividad.class);
	}
	
	public static ActividadDAO getInstance(){
		if(daoAct==null)
			daoAct=new ActividadDAO();
		return daoAct;
	}
}
