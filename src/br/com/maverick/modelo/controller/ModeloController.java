<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/ModeloRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.modelo.controller;
>>>>>>> Stashed changes:src/br/com/maverick/modelo/controller/ModeloController.java

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

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/ModeloRestFacade.java
import br.com.maverick.model.model.ModeloModel;
import br.com.maverick.service.model.ModeloServiceInterface;
=======
import br.com.maverick.modelo.model.ModeloModel;
import br.com.maverick.modelo.service.ModeloService;
>>>>>>> Stashed changes:src/br/com/maverick/modelo/controller/ModeloController.java

@Path("/modelos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ModeloController {

	@Inject
	private ModeloService modeloService;
	
	@GET
	public List<ModeloModel> getModelos(){
		return modeloService.getModelos();
	}
	
	@GET
	@Path("/{codModelo}")
	public List<ModeloModel> getModelos(@PathParam("codModelo") Integer codModelo){
		return modeloService.getModelos(codModelo);
	}
	
	@POST
	public ModeloModel salvar(ModeloModel modeloModel) {
		return modeloService.salvarModelo(modeloModel);
	}
	
	@PUT
	public void alterar(ModeloModel modeloModel) {
		modeloService.alterar(modeloModel);
	}
	
	@DELETE
	@Path("/{codModelo}")
	public void excluir(@PathParam("codModelo") Integer codModelo) {
		ModeloModel modeloModel = new ModeloModel();
		modeloModel.setCodModelo(codModelo);
		modeloService.excluir(modeloModel);
	}
}
