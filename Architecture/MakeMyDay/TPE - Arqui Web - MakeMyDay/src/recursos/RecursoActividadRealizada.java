package recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidades.ActividadRealizada;
import persistencia.ActividadRealizadaDAO;

@Path("/actividad_realizada")
public class RecursoActividadRealizada {
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActividadRealizada getActividadById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		ActividadRealizada act = ActividadRealizadaDAO.getInstance().findById(id);
		return act;

	}
}
