package br.com.maverick.rest.facade;

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

import br.com.maverick.model.UsuarioModel;
import br.com.maverick.service.UsuarioServiceInterface;

@Path("/usuarios")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRestFacade {

	
	@Inject
    UsuarioServiceInterface usuarioServiceInterface;

	@GET
	public List<UsuarioModel> getUsuarios(){
		return usuarioServiceInterface.getUsuarios();
	}
	
	@GET
	@Path("/{codUsuario}")
	public  List<UsuarioModel> getUsuarios(@PathParam("codUsuario") Integer codUsuario){
		return usuarioServiceInterface.getUsuarios(codUsuario);
	}
	
	@POST
	public UsuarioModel salvarUsuario (UsuarioModel usuarioModel) {
		return usuarioServiceInterface.salvarUsuario(usuarioModel);
	}
	
	
	@PUT
	public void alterar(UsuarioModel usuarioModel) {
		usuarioServiceInterface.alterar(usuarioModel);
	}
	
	@DELETE
	@Path("/{codUsuario}")
	public void excluir(@PathParam("codUsuario") Integer codUsuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setCodUsuario (codUsuario);
		usuarioServiceInterface.excluir(usuarioModel);
	}


}
