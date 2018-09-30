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

import br.com.maverick.model.model.ClienteModel;
import br.com.maverick.service.model.ClienteServiceInterface;

@Path("/clientes")
@Produces({MediaType.APPLICATION_JSON,
		   MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)

public class ClienteRestFacade {

	@Inject
	private ClienteServiceInterface clienteServiceInterface;
	
	@GET
	public List<ClienteModel> getClientes(){
		return clienteServiceInterface.getClientes();
	}
	
	@POST
	public ClienteModel salvarCliente(ClienteModel clienteModel) {
		return clienteServiceInterface.salvarCliente(clienteModel);
	}
	
	@PUT
	public void atualizar(ClienteModel clienteModel) {
		clienteServiceInterface.alterar(clienteModel);
	}
	
	@DELETE
	@Path("/{codigoCliente}")
	public void excluir(@PathParam("codigoCliente") Integer codigoCliente) {
		ClienteModel clienteModel = new ClienteModel();
		clienteModel.setCodCliente(codigoCliente);
		clienteServiceInterface.excluir(clienteModel);
	}
	
}
