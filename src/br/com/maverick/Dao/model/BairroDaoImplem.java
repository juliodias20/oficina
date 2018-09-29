package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.BairroModel;

public class BairroDaoImplem implements BairroDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public BairroModel salvarBairro(BairroModel bairroModel) {
		entityManager.persist(bairroModel);
		return bairroModel;
	}
	
	@Override
	public void alterar(BairroModel bairroModel) {
		BairroModel bairroModelMerge =  entityManager.merge(bairroModel);
		entityManager.persist(bairroModelMerge);
	}

	@Override
	public void excluir(BairroModel bairroModel) {
		BairroModel  bairroModelMerge = entityManager.merge(bairroModel);
		entityManager.remove(bairroModelMerge);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BairroModel> getBairros() {
		Query query = entityManager.createQuery("from BairroModel");
		return query.getResultList();
	}

}
