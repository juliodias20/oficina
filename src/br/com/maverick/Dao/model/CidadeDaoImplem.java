package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.CidadeModel;

public class CidadeDaoImplem implements CidadeDaoInterface{

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public CidadeModel salvarCidade(CidadeModel cidadeModel) {
		entityManager.persist(cidadeModel);
		return cidadeModel;
	}

	@Override
	public void alterar(CidadeModel cidadeModel) {
		CidadeModel cidadeModelMerge = entityManager.merge(cidadeModel);
		entityManager.persist(cidadeModelMerge);		
	}

	
	@Override
	public void excluir(CidadeModel cidadeModel) {
		CidadeModel cidadeModelMerge = entityManager.merge(cidadeModel);
		entityManager.remove(cidadeModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CidadeModel> getCidades() {
		Query query = entityManager.createQuery("from CidadeModel");
		return query.getResultList();
	}

}
