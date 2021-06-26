<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/ProdutoRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.produto.controller;
>>>>>>> Stashed changes:src/br/com/maverick/produto/controller/ProdutoController.java

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

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/ProdutoRestFacade.java
import br.com.maverick.model.model.ProdutoModel;
import br.com.maverick.service.model.ProdutoServiceInterface;
=======
import br.com.maverick.produto.model.ProdutoModel;
import br.com.maverick.produto.service.ProdutoService;
>>>>>>> Stashed changes:src/br/com/maverick/produto/controller/ProdutoController.java

@Path("/produtos")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

	@Inject
    private ProdutoService produtoService;
	
	@GET
	public List<ProdutoModel> getProdutos(){
		return produtoService.getProdutos();
	}
	
	@GET
	@Path("/{codProduto}")
	public List<ProdutoModel> getProdutos(@PathParam("codProduto") Integer codProduto){
		return produtoService.getProdutos(codProduto);
	}
	
	@POST
	public ProdutoModel salvar(ProdutoModel produtoModel) {
		return produtoService.salvarProduto(produtoModel);
	}
	
	@PUT
	public void alterar(ProdutoModel produtoModel) {
		produtoService.alterar(produtoModel);
	}
	
	@DELETE
	@Path("/{codProduto}")
	public void excluir(@PathParam("codProduto") Integer codProduto) {
		ProdutoModel produtoModel = new ProdutoModel();
		produtoModel.setCodProduto(codProduto);
		produtoService.excluir(produtoModel);
	}
	
}
