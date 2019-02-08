package persistencia;

import entidades.ActividadRealizada;

public class ActividadRealizadaDAO extends BaseJPADAO<ActividadRealizada>{

	private static ActividadRealizadaDAO daoAct;

	private ActividadRealizadaDAO() {
	super(ActividadRealizada.class);
	}
	
	public static ActividadRealizadaDAO getInstance(){
		if(daoAct==null)
			daoAct=new ActividadRealizadaDAO();
		return daoAct;
	}
}
