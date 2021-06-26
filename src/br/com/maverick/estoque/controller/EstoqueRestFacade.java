<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/EstoqueRestFacade.java
package br.com.maverick.rest.facade.model;
=======
package br.com.maverick.estoque.controller;
>>>>>>> Stashed changes:src/br/com/maverick/estoque/controller/EstoqueRestFacade.java

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

<<<<<<< Updated upstream:src/br/com/maverick/rest/facade/model/EstoqueRestFacade.java
import br.com.maverick.model.model.EstoqueModel;
import br.com.maverick.service.model.EstoqueServiceInterface;
=======
import br.com.maverick.estoque.model.EstoqueModel;
import br.com.maverick.estoque.service.EstoqueService;
>>>>>>> Stashed changes:src/br/com/maverick/estoque/controller/EstoqueRestFacade.java

@Path("/estoques")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueRestFacade {

	@Inject
	private EstoqueService estoqueService;

	@GET
	public List<EstoqueModel> getEstoques() {
		return estoqueService.getEstoques();
	}

	@GET
	@Path("/{codProduto}")
	public List<EstoqueModel> getEstoquesPorProduto(@PathParam("codProduto") Integer codProduto) {
		return estoqueService.getEstoquesPorProduto(codProduto);
	}

	@POST
	public EstoqueModel salvarEstoque(EstoqueModel estoqueModel) {
		return estoqueService.salvarEstoque(estoqueModel);
	}

	@PUT
	public void alterar(EstoqueModel estoqueModel) {
		estoqueService.alterar(estoqueModel);
	}
	
	@PUT
	@Path("/{codProduto}/{qtdEstoque}")
	public void alterar(@PathParam("codProduto") Integer codProduto,
						@PathParam("qtdEstoque") Integer qtdEstoque) {
		estoqueService.alterar(codProduto, qtdEstoque);
	}

}
