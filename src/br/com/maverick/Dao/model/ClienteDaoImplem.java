package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.ClienteModel;

public class ClienteDaoImplem implements ClienteDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;

	@Override
	public ClienteModel salvarCliente(ClienteModel clienteModel) {
		entityManager.persist(clienteModel);
		return clienteModel;
	}

	@Override
	public void alterar(ClienteModel clienteModel) {
		ClienteModel clienteModelMerge = entityManager.merge(clienteModel);
		entityManager.persist(clienteModelMerge);		
	}

	@Override
	public void excluir(ClienteModel clienteModel) {
		ClienteModel clienteModelMerge = entityManager.merge(clienteModel);
		entityManager.remove(clienteModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ClienteModel> getClientes() {
		Query query = entityManager.createQuery("from ClienteModel");
		return query.getResultList();
	}
	
}
