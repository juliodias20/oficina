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

import br.com.maverick.model.model.MarcaModel;
import br.com.maverick.service.model.MarcaServiceInterface;

@Path("/marcas")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class MarcaRestFacade {

	@Inject
	private MarcaServiceInterface marcaServiceInterface;
	
	@GET
	public List<MarcaModel> getMarcas(){
		return marcaServiceInterface.getMarcas();
	}
	
	@POST
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		return marcaServiceInterface.salvarMarca(marcaModel);
	}
	
	@PUT
	public void alterar(MarcaModel marcaModel) {
		marcaServiceInterface.alterar(marcaModel);
	}
	
	@DELETE
	@Path("/{codMarca}")
	public void excluir(@PathParam("codMarca") Integer codMarca) {
		MarcaModel marcaModel = new MarcaModel();
		marcaModel.setCodMarca(codMarca);
		marcaServiceInterface.excluir(marcaModel);
	}
	
	
	
}
