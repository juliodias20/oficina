<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/PerfilDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.perfil.repository;
>>>>>>> Stashed changes:src/br/com/maverick/perfil/repository/PerfilDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/PerfilDaoImplem.java
import br.com.maverick.model.model.PerfilModel;
=======
import br.com.maverick.perfil.model.PerfilModel;
>>>>>>> Stashed changes:src/br/com/maverick/perfil/repository/PerfilDao.java

public class PerfilDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public PerfilModel salvarPerfil(PerfilModel perfilModel) {
		entityManager.persist(perfilModel);
		return perfilModel;
	}

	public void alterar(PerfilModel perfilModel) {
		PerfilModel perfilModelMerge = entityManager.merge(perfilModel);
		entityManager.persist(perfilModelMerge);		
	}

	public void excluir(PerfilModel perfilModel) {
		PerfilModel perfilModelMerge = entityManager.merge(perfilModel);
		entityManager.remove(perfilModelMerge);	
	}

	@SuppressWarnings("unchecked")
	public List<PerfilModel> getPerfis() {
		Query query = entityManager.createQuery("from PerfilModel");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PerfilModel> getPerfis(Integer codPerfil) {
		Query query = entityManager.createQuery("from PerfilModel where p.codPerfil = :id");
			  query.setParameter("id", codPerfil);
		return query.getResultList();
	}
	
}
