package br.com.maverick.rest.facade.model;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/historicoproduto")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class HistoricoProdutoPrecoRestFacade {
/*
	@Inject
	HistoricoPrecoProdutoServiceInterface historicoPrecoProdutoServiceInterface;
	
	@GET
	@Path("/{codProduto}")
	public List<HistoricoPrecoProduto> getTodosHistoricoProduto(@PathParam("codProduto") Integer codProduto){
		return historicoPrecoProdutoServiceInterface.getTodosHistoricoProduto(codProduto);
	}
	
	@GET
	@Path("/{codProduto}/{dhref}")
	public List<HistoricoPrecoProduto> getHistoricoDhref(@PathParam("codProduto") Integer codProduto,
														  @PathParam("dhref") Timestamp dhref){
		return historicoPrecoProdutoServiceInterface.getHistoricoDhref(codProduto, dhref);
	}
	
	*/
}
