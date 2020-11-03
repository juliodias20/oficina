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

import br.com.maverick.model.PerfilModel;
import br.com.maverick.service.PerfilServiceInterface;

@Path("/perfis")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilRestFacade {

	@Inject
	PerfilServiceInterface perfilServiceInterface;
	
	@GET
	public List<PerfilModel> getPerfis(){
		return perfilServiceInterface.getPerfis();
	}
	
	@GET
	@Path("/{codPerfil}")
	public List<PerfilModel> getPerfis(@PathParam("codPerfil") Integer codPerfil){
		return perfilServiceInterface.getPerfis(codPerfil);
	}
	
	@POST
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		return perfilServiceInterface.salvarPerfil(perfilModel);
	}
	
	
	@PUT
	public void alterar(PerfilModel perfilModel) {
		perfilServiceInterface.alterar(perfilModel);
	}
	
	@DELETE
	@Path("/{codPerfil}")
	public void excluir(@PathParam("codPerfil") Integer codPerfil) {
		PerfilModel perfilModel = new PerfilModel();
		perfilModel.setCodPerfil(codPerfil);
		perfilServiceInterface.excluir(perfilModel);
	}
	
	
}
