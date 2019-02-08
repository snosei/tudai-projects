package recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import entidades.Usuario;
import persistencia.UsuarioDAO;
import recursos.autorizacion.Credencial;
import recursos.autorizacion.Secured;
import recursos.autorizacion.TokenHelper;

@Path("/autenticacion")
public class RecursoAutentificacion {
	@Context
	SecurityContext securityContext;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response autenticarUser(Credencial credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		try {

			// Authenticate the user using the credentials provided
			autenticar(username, password);

			// Issue a token for the user
			String token = emitirToken(username);

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		// Authenticate the user, issue a token and return a response
	}

	@POST
	@Path("/logout")
	@Secured
	public Response desautenticarUser(@HeaderParam("Authorization") String token) {
		String user = TokenHelper.removeToken(parseToken(token));
		Status response = Response.Status.OK;
		if(user==null)
			response = Response.Status.UNAUTHORIZED;
		return Response.status(response).build();
	}

	private String parseToken(String header) {
		String token = header.substring("Bearer".length()).trim();
		return token;
	}
	
	private void autenticar(String username, String password) throws Exception {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		Usuario user = new Usuario();
		user.setNombre(username);
		user.setPassword(password);
		user = UsuarioDAO.login(user);
		if (!user.esValido()) {
			throw new RuntimeException();
		}

	}

	private String emitirToken(String userName) {
		// Issue a token (can be a random String persisted to a database or a
		// JWT token)
		// The issued token must be associated to a user
		// Return the issued token
		String token = TokenHelper.generarToken(userName);
		TokenHelper.setToken(token, userName);
		return token;
	}
}
