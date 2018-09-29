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

import br.com.maverick.model.model.EstadoModel;
import br.com.maverick.service.model.EstadoServiceInterface;

@Path("/estados")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class EstadosRestFacade {
	
	@Inject
	private EstadoServiceInterface estadoServiceInterface;
	
	@GET
	public List<EstadoModel> getEstados() {
		return estadoServiceInterface.getEstados();
	}
	
	@POST
	public EstadoModel salvarEstado(EstadoModel estadoModel) {
		return estadoServiceInterface.salvarEstado(estadoModel);
	}
	
	@PUT
	public void atualizar(EstadoModel estadoModel) {
		estadoServiceInterface.alterar(estadoModel);
	}

	@DELETE
	@Path("/{codigoEstado}")
	public void excluir(@PathParam("codigoEstado") Integer codigoEstado) {
		EstadoModel estadoModel = new EstadoModel();
		estadoModel.setCodigo(codigoEstado);
		estadoServiceInterface.excluir(estadoModel);
	}
		
}
	

