package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.OsModel;

public class OsDaoImplem implements OsDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public OsModel salvarOs(OsModel osModel) {
		entityManager.persist(osModel);
		return osModel;
	}

	@Override
	public void alterar(OsModel osModel) {
		OsModel osModelMerge = entityManager.merge(osModel);
		entityManager.persist(osModelMerge);
	}

	@Override
	public void excluir(OsModel osModel) {
		OsModel osModelMerge = entityManager.merge(osModel);
		entityManager.remove(osModelMerge);
	}

	//get todas as OSs
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOs() {
		Query query = entityManager.createQuery("from OsModel");
		return query.getResultList();
	}
	
	//get uma OS pelo NUMERO
	@Override
	@SuppressWarnings("unchecked")
	public List<OsModel> getOs(Integer numOs) {
		Query query = entityManager.createQuery("from OsModel os where os.numos = :numOs");
			  query.setParameter("numOs", numOs);
		return query.getResultList();
	}

}
