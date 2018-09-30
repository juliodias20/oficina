package br.com.maverick.service.model;

import java.util.List;

import br.com.maverick.model.model.ClienteModel;

public interface ClienteServiceInterface {

	ClienteModel salvarCliente(ClienteModel clienteModel);
	
	void alterar(ClienteModel clienteModel);
	
	void excluir(ClienteModel clienteModel);
	
	List<ClienteModel> getClientes();
	
	
}
