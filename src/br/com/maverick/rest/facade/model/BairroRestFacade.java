package br.com.maverick.rest.facade.model;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.maverick.model.model.BairroModel;
import br.com.maverick.service.model.BairroServiceInterface;

@Path("/bairros")
@Produces({MediaType.APPLICATION_JSON,
	   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class BairroRestFacade {

	@Inject
	private BairroServiceInterface bairroServiceInterface;
	
	@GET
	public List<BairroModel> getCidades(){
		return bairroServiceInterface.getBairros();
	}
	
	@POST
	public BairroModel salvarBairro(BairroModel bairroModel) {
		return bairroServiceInterface.salvarBairro(bairroModel);
	}
	
	@DELETE
	@Path("/{codigoBairro}")
	public void excluir(@PathParam("codigoBairro") Integer codBairro) {
		BairroModel bairroModel = new BairroModel();
		bairroModel.setCodBairro(codBairro);
		bairroServiceInterface.excluir(bairroModel);
	}
	
	
	
	
}
