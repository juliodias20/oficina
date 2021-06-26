<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/LoginRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.login.controller;
>>>>>>> Stashed changes:src/br/com/maverick/login/controller/LoginRestFacade.java

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/LoginRestFacade.java
import br.com.maverick.model.model.UsuarioModel;
import br.com.maverick.service.model.LoginServiceInterface;
=======
import br.com.maverick.login.service.LoginService;
import br.com.maverick.usuario.model.UsuarioModel;
>>>>>>> Stashed changes:src/br/com/maverick/login/controller/LoginRestFacade.java

@Path("/login")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class LoginRestFacade {
	
	@Inject
    LoginService loginService;
	
	@POST
	public UsuarioModel login(UsuarioModel usuarioModel) {
		return loginService.login(usuarioModel);
	}

}
