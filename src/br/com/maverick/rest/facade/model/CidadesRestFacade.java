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

import br.com.maverick.model.model.CidadeModel;
import br.com.maverick.service.model.CidadeServiceInterface;

@Path("/cidades")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)		
		
public class CidadesRestFacade {

	@Inject
	private CidadeServiceInterface cidadeServiceInterface;
	
	@GET
	public List<CidadeModel> getCidades(){
		return cidadeServiceInterface.getCidades();
	}
	
	@POST
	public CidadeModel salvarCidade(CidadeModel cidadeModel) {
		return cidadeServiceInterface.salvarCidade(cidadeModel);
	}
	
	@PUT
	public void atualizar(CidadeModel cidadeModel) {
		cidadeServiceInterface.alterar(cidadeModel);
	}
	
	@DELETE
	@Path("/{codigoCidade}")
	public void excluir(@PathParam("codigoCidade") Integer codigoCidade) {
		CidadeModel cidadeModel = new CidadeModel();
		cidadeModel.setCodigo(codigoCidade);
		cidadeServiceInterface.excluir(cidadeModel);		
	}
	
}
