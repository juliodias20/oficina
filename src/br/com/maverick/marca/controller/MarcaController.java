<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/MarcaRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.marca.controller;
>>>>>>> Stashed changes:src/br/com/maverick/marca/controller/MarcaController.java

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

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/MarcaRestFacade.java
import br.com.maverick.model.model.MarcaModel;
import br.com.maverick.service.model.MarcaServiceInterface;
=======
import br.com.maverick.marca.model.MarcaModel;
import br.com.maverick.marca.service.MarcaService;
>>>>>>> Stashed changes:src/br/com/maverick/marca/controller/MarcaController.java

@Path("/marcas")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class MarcaController {

	@Inject
	private MarcaService marcaService;
	
	@GET
	public List<MarcaModel> getMarcas(){
		return marcaService.getMarcas();
	}
	
	@POST
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		return marcaService.salvarMarca(marcaModel);
	}
	
	@PUT
	public void alterar(MarcaModel marcaModel) {
		marcaService.alterar(marcaModel);
	}
	
	@DELETE
	@Path("/{codMarca}")
	public void excluir(@PathParam("codMarca") Integer codMarca) {
		MarcaModel marcaModel = new MarcaModel();
		marcaModel.setCodMarca(codMarca);
		marcaService.excluir(marcaModel);
	}
	
}
