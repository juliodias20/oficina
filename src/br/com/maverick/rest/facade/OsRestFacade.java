package br.com.maverick.rest.facade;

import java.sql.Date;
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

import br.com.maverick.model.OsModel;
import br.com.maverick.service.OsServiceInterface;

@Path("/os")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class OsRestFacade {
	
	//injeta a interface do serviço que comunica com a tabela de OS do banco de dados
	@Inject
	OsServiceInterface osServiceInterface;

	// insere uma uma nova OS
	@POST
	@Path("/pendentes")
	public OsModel salvar(OsModel osModel) {
		return osServiceInterface.salvarOs(osModel);
	}
	
	// atualiza uma OS que já existe
	@PUT
	@Path("/pendentes")
	public void alterar(OsModel osModel) {
		osServiceInterface.alterar(osModel);
	}	
	
	// atualiza apenas o valor total da OS
	@PUT
	@Path("/pendentes/{numOs}/{valorTotal}")
	public void alterar(@PathParam("numOs") Integer numOs,
						@PathParam("valorTotal") float valorTotal) {
		osServiceInterface.alterar(numOs, valorTotal);
	}
	
	//recebe a PK de uma OS e delete ela do banco
	@DELETE
	@Path("/pendentes/{numOs}")
	public void excluir(@PathParam("numOs") Integer numOs) {
		OsModel osModel = new OsModel();
		osModel.setNumOS(numOs);
		osServiceInterface.excluir(osModel);
	}

	// busca uma única OS pela PK, passando a PK de parametro, ou todas as encerradas passando -1 no parametro
	@GET
	@Path("/pendentes")
	public List<OsModel> getOsPendentes(){
		return osServiceInterface.getOsPendentes();
	}
	
	@GET
	@Path("pendentes/{numOs}")
	public List<OsModel> getOsPendente(@PathParam("numOs") Integer numOs){
		return osServiceInterface.getOsPendente(numOs);
	}
	
	@GET
	@Path("/encerradas")
	public List<OsModel> getOsEncerradas(){
		return osServiceInterface.getOsEncerradas();
	}
	
	//busca OSs por data de abertura entre
	@GET
	@Path("/encerradas/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOsEncerradas( @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osServiceInterface.getOsEncerradas(dtIni, dtFim);
	}
	
	//busca OSs por  parceiro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}")
	public List<OsModel> getOsParceiro(@PathParam("codParceiro") Integer codParceiro){
		return osServiceInterface.getOsEncerradas(codParceiro);
	}
	
	//busca OSs por placa de carro
	@GET
	@Path("/encerradas/placa/{placa}")
	public List<OsModel> getOs(@PathParam("placa") String placa){
		return osServiceInterface.getOsEncerradas(placa);
	}
	
	//busca OSs por parceiro e placa de carro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/placa/{placa}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("placa") String placa){
		return osServiceInterface.getOsEncerradas(codParceiro, placa);
	}
	
	//busca OSs por parceiro e data de abertura entre
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osServiceInterface.getOsEncerradas(codParceiro, dtIni, dtFim);
	}
	
	//busca OSs por periodo e placa do carro
	@GET
	@Path("/encerradas/placa/{placa}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("placa") String placa, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osServiceInterface.getOsEncerradas(placa, dtIni, dtFim);
	}
	
	//busca OSs por parceiro, periodo e placa do carro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/placa/{placa}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("placa") String placa, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osServiceInterface.getOsEncerradas(codParceiro, placa, dtIni, dtFim);
	}
		
}
