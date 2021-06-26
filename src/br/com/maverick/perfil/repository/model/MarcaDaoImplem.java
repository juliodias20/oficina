<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/MarcaDaoImplem.java
package br.com.maverick.Dao.model;
=======
package br.com.maverick.marca.repository;
>>>>>>> Stashed changes:src/br/com/maverick/marca/repository/MarcaDao.java

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

<<<<<<< Updated upstream:src/br/com/maverick/perfil/repository/model/MarcaDaoImplem.java
import br.com.maverick.model.model.MarcaModel;
=======
import br.com.maverick.marca.model.MarcaModel;
>>>>>>> Stashed changes:src/br/com/maverick/marca/repository/MarcaDao.java

public class MarcaDao {

	@PersistenceContext(unitName="OficinaPersistenceUnit")
	private EntityManager entityManager;
	
	public MarcaModel salvarMarca(MarcaModel marcaModel) {
		entityManager.persist(marcaModel);
		return marcaModel;
	}

	public void alterar(MarcaModel marcaModel) {
		MarcaModel marcaModelMerge = entityManager.merge(marcaModel);
		entityManager.persist(marcaModelMerge);	
	}

	public void excluir(MarcaModel marcaModel) {
		MarcaModel marcaModelMerge = entityManager.merge(marcaModel);
		entityManager.remove(marcaModelMerge);
	}

	@SuppressWarnings("unchecked")
	public List<MarcaModel> getMarcas() {
		Query query = entityManager.createQuery("from MarcaModel");
		return query.getResultList();
	}

}
