package br.com.maverick.parceiro.controller;

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

import br.com.maverick.parceiro.model.ParceiroModel;
import br.com.maverick.parceiro.service.ParceiroService;

@Path("/parceiros")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class ParceiroController {

	@Inject
	private ParceiroService parceiroService;
	
	@GET
	public List<ParceiroModel> getParceiros(){
		return parceiroService.getParceiros();
	}
	
	@GET
	@Path("/{codParceiro}")
	public List<ParceiroModel> getParceiros(@PathParam("codParceiro") Integer codParceiro){
		return parceiroService.getParceiros(codParceiro);
	}
	
	@GET
	@Path("/{parametro}/{tipoParametro}")
	public List<ParceiroModel> getParceiros(@PathParam("parametro")String parametro,
										    @PathParam("tipoParametro")String tipoParametro){
		
		return parceiroService.getParceiros(parametro, tipoParametro);
	}
	
	@POST
	public ParceiroModel salvarParceiro(ParceiroModel parceiroModel) {
		return parceiroService.salvarParceiro(parceiroModel);
	}
	
	@PUT
	public void atualizar(ParceiroModel parceiroModel) {
		parceiroService.alterar(parceiroModel);
	}
	
	@DELETE
	@Path("/{codigoParceiro}")
	public void excluir(@PathParam("codigoParceiro") Integer codigoParceiro) {
		ParceiroModel parceiroModel = new ParceiroModel();
		parceiroModel.setCodParceiro(codigoParceiro);
		parceiroService.excluir(parceiroModel);
	}	
}
