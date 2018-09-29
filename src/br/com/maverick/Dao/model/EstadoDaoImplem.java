package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.EstadoModel;

public class EstadoDaoImplem implements EstadoDaoInterface{

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public EstadoModel salvarEstado(EstadoModel estadoModel) {
		entityManager.persist(estadoModel);
		return estadoModel;
	}

	@Override
	public void alterar(EstadoModel estadoModel) {
		EstadoModel estadoModelMerge = entityManager.merge(estadoModel);
		entityManager.persist(estadoModelMerge);		
	}

	@Override
	public void excluir(EstadoModel estadoModel) {
		EstadoModel estadoModelMerge = entityManager.merge(estadoModel);
		entityManager.remove(estadoModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<EstadoModel> getEstados() {
		Query query = entityManager.createQuery("from EstadoModel");
		return query.getResultList();
	}

}
