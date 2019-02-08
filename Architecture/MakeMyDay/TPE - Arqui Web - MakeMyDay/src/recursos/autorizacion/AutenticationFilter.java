package recursos.autorizacion;


import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutenticationFilter implements ContainerRequestFilter {
	public void filter(ContainerRequestContext ctx) throws IOException {
		String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authHeader == null||authHeader.isEmpty()) throw new NotAuthorizedException("bad token");
		// Extract the token from the HTTP Authorization header
		String token = parseToken(authHeader);
		if (verifyToken(token) == false) {
			throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
		}
		final String userName = TokenHelper.getUserName(token);
		ctx.setSecurityContext(new SecurityContext() {
			@Override
			public Principal getUserPrincipal() {
				return new Principal() {
					@Override
					public String getName() {
						return userName;
					}
				};
			}
			@Override
			public boolean isUserInRole(String role) {
				return true;
			}
			@Override
			public boolean isSecure() {
				return false;
			}
			@Override
			public String getAuthenticationScheme() {
				return null;
			}
		});

	}

	private String parseToken(String header) {
		String token = header.substring("Bearer".length()).trim();
		return token;
	}
	private boolean verifyToken(String token) {
		return TokenHelper.isValidoToken(token);
	}
}