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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClienteModel> getClientes(Integer parametro){
			
		Query query = entityManager.createQuery("from ClienteModel c where c.codCliente = :codCliente");
			  query.setParameter("codCliente", parametro);
			 
		return query.getResultList();
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ClienteModel> getClientes(String parametro, String tipoParametro){
		System.out.println("GET COM 2 PARAMETROS");
		if(tipoParametro.equals("cpf")) {
			Query query = entityManager.createQuery("from ClienteModel c where c.cpf = :cpf");
				  query.setParameter("cpf", parametro);
			return query.getResultList();
		}else if(tipoParametro.equals("cnpj")) {
			Query query = entityManager.createQuery("from ClienteModel c where c.cnpj = :cnpj");
				  query.setParameter("cnpj", parametro);
			return query.getResultList();
		}else if (tipoParametro.equals("codCliente")) {
			Query query = entityManager.createQuery("from ClienteModel c where c.codCliente = :codCliente");
				  query.setParameter("codCliente", parametro);
			return query.getResultList();
		}else {
			System.out.println("caiu no else");
			return null;
		}
	}
	
	
}
