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

import br.com.maverick.model.model.OsModel;
import br.com.maverick.service.model.OsServiceInterface;

@Path("/os")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class OsRestFacade {
	
	//injeta a interface do serviço que comunica com a tabela de OS do banco de dados
	@Inject
	OsServiceInterface osServiceInterface;
	
	// busca todas as OS do banco de dados e retorna um Array
	@GET
	public List<OsModel> getOs(){
		return osServiceInterface.getOs();
	}
	
	// busca uma única OS pela PK, passando a PK de parametro
	@GET
	@Path("/{numOs}")
	public List<OsModel> getOs(@PathParam("numOs") Integer numOs){
		return osServiceInterface.getOs(numOs);
	}
	
	// insere uma uma nova OS
	@POST
	public OsModel salvar(OsModel osModel) {
		return osServiceInterface.salvarOs(osModel);
	}
	
	// atualiza uma OS que já existe
	@PUT
	public void alterar(OsModel osModel) {
		osServiceInterface.alterar(osModel);
	}	
	
	// atualiza apenas o valor total da OS
	@PUT
	@Path("/{numOs}/{valorTotal}")
	public void alterar(@PathParam("numOs") Integer numOs,
						@PathParam("valorTotal") float valorTotal) {
		osServiceInterface.alterar(numOs, valorTotal);
	}
	
	//recebe a PK de uma OS e delete ela do banco
	@DELETE
	@Path("/{numOs}")
	public void excluir(@PathParam("numOs") Integer numOs) {
		OsModel osModel = new OsModel();
		osModel.setNumOS(numOs);
		osServiceInterface.excluir(osModel);
	}
}
