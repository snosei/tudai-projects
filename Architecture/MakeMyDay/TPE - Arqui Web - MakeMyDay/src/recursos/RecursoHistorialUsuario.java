package recursos;

import java.security.Principal;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import entidades.HistorialUsuario;
import persistencia.HistorialUsuarioDAO;
import recursos.autorizacion.Secured;
import utils.ParFechas;

@Path("/historial_usuario")
public class RecursoHistorialUsuario {
	@Context
	SecurityContext securityContext;

	@POST
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<HistorialUsuario> getActividadesEntreFechas(ParFechas datePair){
		Principal up = securityContext.getUserPrincipal();
		String userName = up.getName();
		return HistorialUsuarioDAO.getInstance().buscarEntreFechas(userName,datePair.getFromDate(),datePair.getToDate());
	}
}
