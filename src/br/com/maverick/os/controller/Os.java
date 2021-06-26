package br.com.maverick.os.controller;

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

import br.com.maverick.os.model.OsModel;
import br.com.maverick.os.service.OsService;

@Path("/os")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class Os {
	

	@Inject
	private OsService osService;

	@POST
	@Path("/pendentes")
	public OsModel salvar(OsModel osModel) {
		return osService.salvarOs(osModel);
	}
	
	// atualiza uma OS que já existe
	@PUT
	@Path("/pendentes")
	public void alterar(OsModel osModel) {
		osService.alterar(osModel);
	}	
	
	// atualiza apenas o valor total da OS
	@PUT
	@Path("/pendentes/{numOs}/{valorTotal}")
	public void alterar(@PathParam("numOs") Integer numOs,
						@PathParam("valorTotal") float valorTotal) {
		osService.alterar(numOs, valorTotal);
	}
	
	@DELETE
	@Path("/pendentes/{numOs}")
	public void excluir(@PathParam("numOs") Integer numOs) {
		OsModel osModel = new OsModel();
		osModel.setNumOS(numOs);
		osService.excluir(osModel);
	}

	@GET
	@Path("/pendentes")
	public List<OsModel> getOsPendentes(){
		return osService.getOsPendentes();
	}
	
	@GET
	@Path("pendentes/{numOs}")
	public List<OsModel> getOsPendente(@PathParam("numOs") Integer numOs){
		return osService.getOsPendente(numOs);
	}
	
	@GET
	@Path("/encerradas")
	public List<OsModel> getOsEncerradas(){
		return osService.getOsEncerradas();
	}
	
	//busca OSs por data de abertura entre
	@GET
	@Path("/encerradas/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOsEncerradas( @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osService.getOsEncerradas(dtIni, dtFim);
	}
	
	//busca OSs por  parceiro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}")
	public List<OsModel> getOsParceiro(@PathParam("codParceiro") Integer codParceiro){
		return osService.getOsEncerradas(codParceiro);
	}
	
	//busca OSs por placa de carro
	@GET
	@Path("/encerradas/placa/{placa}")
	public List<OsModel> getOs(@PathParam("placa") String placa){
		return osService.getOsEncerradas(placa);
	}
	
	//busca OSs por parceiro e placa de carro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/placa/{placa}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("placa") String placa){
		return osService.getOsEncerradas(codParceiro, placa);
	}
	
	//busca OSs por parceiro e data de abertura entre
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osService.getOsEncerradas(codParceiro, dtIni, dtFim);
	}
	
	//busca OSs por periodo e placa do carro
	@GET
	@Path("/encerradas/placa/{placa}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("placa") String placa, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osService.getOsEncerradas(placa, dtIni, dtFim);
	}
	
	//busca OSs por parceiro, periodo e placa do carro
	@GET
	@Path("/encerradas/parceiro/{codParceiro}/placa/{placa}/dtabertura/{dtIni}/{dtFim}")
	public List<OsModel> getOs(@PathParam("codParceiro") Integer codParceiro, @PathParam("placa") String placa, @PathParam("dtIni") Date dtIni, @PathParam("dtFim") Date dtFim){
		return osService.getOsEncerradas(codParceiro, placa, dtIni, dtFim);
	}
		
}
