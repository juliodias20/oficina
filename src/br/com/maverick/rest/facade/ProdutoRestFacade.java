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

import br.com.maverick.model.ProdutoModel;
import br.com.maverick.service.ProdutoServiceInterface;

@Path("/produtos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoRestFacade {

	@Inject
    ProdutoServiceInterface produtoServiceInterface;
	
	@GET
	public List<ProdutoModel> getProdutos(){
		return produtoServiceInterface.getProdutos();
	}
	
	@GET
	@Path("/{codProduto}")
	public List<ProdutoModel> getProdutos(@PathParam("codProduto") Integer codProduto){
		return produtoServiceInterface.getProdutos(codProduto);
	}
	
	@POST
	public ProdutoModel salvar(ProdutoModel produtoModel) {
		return produtoServiceInterface.salvarProduto(produtoModel);
	}
	
	@PUT
	public void alterar(ProdutoModel produtoModel) {
		produtoServiceInterface.alterar(produtoModel);
	}
	
	@PUT
	@Path("/{codProduto}/{qtdEstoque}")
	public void alterar(@PathParam("codProduto") Integer codProduto,
						@PathParam("qtdEstoque") Integer qtdEstoque) {
		produtoServiceInterface.alterar(codProduto, qtdEstoque);
	}
	
	@DELETE
	@Path("/{codProduto}")
	public void excluir(@PathParam("codProduto") Integer codProduto) {
		ProdutoModel produtoModel = new ProdutoModel();
		produtoModel.setCodProduto(codProduto);
		produtoServiceInterface.excluir(produtoModel);
	}
	
}
