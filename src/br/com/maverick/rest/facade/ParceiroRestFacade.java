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

import br.com.maverick.model.ParceiroModel;
import br.com.maverick.service.ParceiroServiceInterface;

@Path("/parceiros")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class ParceiroRestFacade {

	@Inject
	private ParceiroServiceInterface parceiroServiceInterface;
	
	@GET
	public List<ParceiroModel> getParceiros(){
		return parceiroServiceInterface.getParceiros();
	}
	
	@GET
	@Path("/{parametro}")
	public List<ParceiroModel> getParceiros(@PathParam("parametro") Integer parametro){
		return parceiroServiceInterface.getParceiros(parametro);
	}
	
	@GET
	@Path("/{parametro}/{tipoParametro}")
	public List<ParceiroModel> getParceiros(@PathParam("parametro")String parametro,
										   @PathParam("tipoParametro")String tipoParametro){
		
		return parceiroServiceInterface.getParceiros(parametro, tipoParametro);
		
	}
	
	@POST
	public ParceiroModel salvarParceiro(ParceiroModel parceiroModel) {
		return parceiroServiceInterface.salvarParceiro(parceiroModel);
	}
	
	@PUT
	public void atualizar(ParceiroModel parceiroModel) {
		parceiroServiceInterface.alterar(parceiroModel);
	}
	
	@DELETE
	@Path("/{codigoParceiro}")
	public void excluir(@PathParam("codigoParceiro") Integer codigoParceiro) {
		ParceiroModel parceiroModel = new ParceiroModel();
		parceiroModel.setCodParceiro(codigoParceiro);
		parceiroServiceInterface.excluir(parceiroModel);
	}
	
}
