<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/PerfilRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.perfil.controller;
>>>>>>> Stashed changes:src/br/com/maverick/perfil/controller/PerfilController.java

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

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/PerfilRestFacade.java
import br.com.maverick.model.model.PerfilModel;
import br.com.maverick.service.model.PerfilServiceInterface;
=======
import br.com.maverick.perfil.model.PerfilModel;
import br.com.maverick.perfil.service.PerfilService;
>>>>>>> Stashed changes:src/br/com/maverick/perfil/controller/PerfilController.java

@Path("/perfis")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilController {

	@Inject
	private PerfilService perfilService;
	
	@GET
	public List<PerfilModel> getPerfis(){
		return perfilService.getPerfis();
	}
	
	@GET
	@Path("/{codPerfil}")
	public List<PerfilModel> getPerfis(@PathParam("codPerfil") Integer codPerfil){
		return perfilService.getPerfis(codPerfil);
	}
	
	@POST
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		return perfilService.salvarPerfil(perfilModel);
	}
	
	@PUT
	public void alterar(PerfilModel perfilModel) {
		perfilService.alterar(perfilModel);
	}
	
	@DELETE
	@Path("/{codPerfil}")
	public void excluir(@PathParam("codPerfil") Integer codPerfil) {
		PerfilModel perfilModel = new PerfilModel();
		perfilModel.setCodPerfil(codPerfil);
		perfilService.excluir(perfilModel);
	}
}
