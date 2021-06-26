<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/UsuarioRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.usuario.controller;
>>>>>>> Stashed changes:src/br/com/maverick/usuario/controller/UsuarioController.java

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/UsuarioRestFacade.java
import br.com.maverick.model.model.UsuarioModel;
import br.com.maverick.service.model.UsuarioServiceInterface;
=======
import br.com.maverick.usuario.model.UsuarioModel;
import br.com.maverick.usuario.service.UsuarioService;
>>>>>>> Stashed changes:src/br/com/maverick/usuario/controller/UsuarioController.java

@Path("/usuarios")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

	
	@Inject
    private UsuarioService usuarioService;

	@GET
	public List<UsuarioModel> getUsuarios(){
		return usuarioService.getUsuarios();
	}
	
	@GET
	@Path("/{codUsuario}")
	public  List<UsuarioModel> getUsuarios(@PathParam("codUsuario") Integer codUsuario){
		return usuarioService.getUsuarios(codUsuario);
	}
	
	@POST
	public UsuarioModel salvarUsuario (UsuarioModel usuarioModel) {
		return usuarioService.salvarUsuario(usuarioModel);
	}
	
	@PUT
	public void alterar(UsuarioModel usuarioModel) {
		usuarioService.alterar(usuarioModel);
	}
	
	@DELETE
	@Path("/{codUsuario}")
	public void excluir(@PathParam("codUsuario") Integer codUsuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setCodUsuario (codUsuario);
		usuarioService.excluir(usuarioModel);
	}


}
