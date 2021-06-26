<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/SubOsRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.osItem.controller;
>>>>>>> Stashed changes:src/br/com/maverick/osItem/controller/SubOsController.java

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

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/SubOsRestFacade.java
import br.com.maverick.model.model.SubOsModel;
import br.com.maverick.service.model.SubOsServiceInterface;
=======
import br.com.maverick.osItem.model.SubOsModel;
import br.com.maverick.osItem.service.SubOsService;
>>>>>>> Stashed changes:src/br/com/maverick/osItem/controller/SubOsController.java

@Path("/subos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class SubOsController {

	@Inject
	private SubOsService subOsService;
	
	@GET
	public List<SubOsModel> getSubOs(){
		return subOsService.getSubOs();
	}
	
	@GET
	@Path("/{numOs}")
	public List<SubOsModel>getSubOs(@PathParam("numOs") Integer numOs){
		return subOsService.getSubOs(numOs);
	}
	
	@GET
	@Path("/{numOs}/{codProduto}")
	public List<SubOsModel>getSubOs(@PathParam("numOs") Integer numOs,
									@PathParam("codProduto") Integer codProduto){
		return subOsService.getSubOs(numOs,codProduto);
	}
	
	@POST
	public SubOsModel salvar(SubOsModel subOsModel) {
		return subOsService.salvarSubOs(subOsModel);
	}
	
	@PUT
	public void alterar(SubOsModel subOsModel) {
		subOsService.alterar(subOsModel);
	}	
	
	@DELETE
	@Path("/{numOs}/{codProduto}")
	public void excluir(@PathParam("numOs") Integer numOs,
						@PathParam("codProduto") Integer codProduto) {
		
		subOsService.excluir(numOs, codProduto);
	}	
	
}
