<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/KitDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.kit.repository;
>>>>>>> Stashed changes:src/br/com/maverick/kit/repository/KitDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/KitDaoImplem.java
import br.com.maverick.model.model.KitModel;
=======
import br.com.maverick.kit.model.KitModel;
>>>>>>> Stashed changes:src/br/com/maverick/kit/repository/KitDao.java

public class KitDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<KitModel> getKits() {
		Query query = entityManager.createQuery("from KitModel");
		List<KitModel> km = query.getResultList();
		return km;
	}

	public KitModel getKitById(Integer codKit) {
		Query query = entityManager.createQuery("from KitModel km where km.codKit = :codKit");
		query.setParameter("codKit", codKit);
		return (KitModel) query.getSingleResult();
	}

}
