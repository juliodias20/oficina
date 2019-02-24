package br.com.maverick.rest.facade.model;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.maverick.model.model.HistoricoPrecoProduto;
import br.com.maverick.service.model.HistoricoPrecoProdutoServiceInterface;

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
