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

import br.com.maverick.model.model.SubOsModel;
import br.com.maverick.service.model.SubOsServiceInterface;

@Path("/subos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class SubOsRestFacade {

	@Inject
	SubOsServiceInterface subOsServiceInterface;
	
	@GET
	public List<SubOsModel> getSubOs(){
		return subOsServiceInterface.getSubOs();
	}
	
	@GET
	@Path("/{numOs}")
	public List<SubOsModel>getSubOs(@PathParam("numOs") Integer numOs){
		return subOsServiceInterface.getSubOs(numOs);
	}
	
	@GET
	@Path("/{numOs}/{codProduto}")
	public List<SubOsModel>getSubOs(@PathParam("numOs") Integer numOs,
									@PathParam("codProduto") Integer codProduto){
		return subOsServiceInterface.getSubOs(numOs,codProduto);
	}
	
	@POST
	public SubOsModel salvar(SubOsModel subOsModel) {
		return subOsServiceInterface.salvarSubOs(subOsModel);
	}
	
	@PUT
	public void alterar(SubOsModel subOsModel) {
		subOsServiceInterface.alterar(subOsModel);
	}	
	
	@DELETE
	@Path("/{numOs}/{codProduto}")
	public void excluir(@PathParam("numOs") Integer numOs,
						@PathParam("codProduto") Integer codProduto) {
		
		subOsServiceInterface.excluir(numOs, codProduto);
	}	
	
}
