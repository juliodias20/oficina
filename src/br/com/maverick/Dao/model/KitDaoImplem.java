package br.com.maverick.Dao.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.maverick.model.model.KitModel;

public class KitDaoImplem implements KitDaoInterface {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<KitModel> getKits() {
		Query query = entityManager.createQuery("from KitModel");
		List<KitModel> km = query.getResultList();
		return km;
	}

	@Override
	public KitModel getKitById(Integer codKit) {
		Query query = entityManager.createQuery("from KitModel km where km.codKit = :codKit");
		query.setParameter("codKit", codKit);
		return (KitModel) query.getSingleResult();
	}

}
