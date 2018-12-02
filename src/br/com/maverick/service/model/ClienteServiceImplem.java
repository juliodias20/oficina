package br.com.maverick.service.model;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.maverick.Dao.model.ClienteDaoInterface;
import br.com.maverick.model.model.ClienteModel;

public class ClienteServiceImplem implements ClienteServiceInterface {

	@Inject
	private ClienteDaoInterface clienteDaoInterface;

	@Override
	@Transactional
	public ClienteModel salvarCliente(ClienteModel clienteModel) {
		return clienteDaoInterface.salvarCliente(clienteModel);
	}

	@Override
	@Transactional
	public void alterar(ClienteModel clienteModel) {
		clienteDaoInterface.alterar(clienteModel);
	}

	@Override
	@Transactional
	public void excluir(ClienteModel clienteModel) {
		clienteDaoInterface.excluir(clienteModel);
	}

	@Override
	public List<ClienteModel> getClientes() {
		return clienteDaoInterface.getClientes();		 
	}
	
	@Override
	public List<ClienteModel> getClientes(String parametro) {
		return clienteDaoInterface.getClientes(parametro);
	}
	
	@Override
	public List<ClienteModel> getClientes(String parametro, String tipoParametro) {
		return clienteDaoInterface.getClientes(parametro, tipoParametro);
	}
	
}
