package br.com.maverick.rest.facade.model;

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

import br.com.maverick.model.model.ModeloModel;
import br.com.maverick.service.model.ModeloServiceInterface;

@Path("/modelos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ModeloRestFacade {

	@Inject
	private ModeloServiceInterface modeloServiceInterface;
	
	
	@GET
	public List<ModeloModel> getModelos(){
		return modeloServiceInterface.getModelos();
	}
	
	
	@GET
	@Path("/{codModelo}")
	public List<ModeloModel> getModelos(@PathParam("codModelo") Integer codModelo){
		return modeloServiceInterface.getModelos(codModelo);
	}
	
	@POST
	public ModeloModel salvar(ModeloModel modeloModel) {
		return modeloServiceInterface.salvarModelo(modeloModel);
	}
	
	@PUT
	public void alterar(ModeloModel modeloModel) {
		modeloServiceInterface.alterar(modeloModel);
	}
	
	@DELETE
	@Path("/{codModelo}")
	public void excluir(@PathParam("codModelo") Integer codModelo) {
		ModeloModel modeloModel = new ModeloModel();
		modeloModel.setCodModelo(codModelo);
		modeloServiceInterface.excluir(modeloModel);
	}
}
