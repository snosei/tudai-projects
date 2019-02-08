package recursos;

import java.util.List;

import javax.ws.rs.ConstrainedTo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidades.Actividad;
import persistencia.ActividadDAO;

@Path("/actividad")
public class RecursoActividad {
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Actividad getActividadById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Actividad act = ActividadDAO.getInstance().findById(id);
		return act;

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actividad> getActividadById() {
		List<Actividad> act = ActividadDAO.getInstance().findAll();
		return act;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createActividad(Actividad newAct){
		ActividadDAO.getInstance().persist(newAct);
	}
}
