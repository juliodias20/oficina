package br.com.maverick.rest.facade.model;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.maverick.model.model.UsuarioModel;
import br.com.maverick.service.model.LoginServiceInterface;

@Path("/login")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRestFacade {
	
	@Inject
    LoginServiceInterface loginServiceInterface;
	
	@POST
	public UsuarioModel login(UsuarioModel usuarioModel) {
		return loginServiceInterface.login(usuarioModel);
	}

	

}
